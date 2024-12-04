package com.mcnz.spring.soap;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import com.documentum.fc.client.IDfSessionManager;
import com.emc.documentum.fs.rt.context.DfcSessionManager;
import com.documentum.fc.client.*;
import com.documentum.fc.common.*;

import com.mcnz.jee.soap.ContentBodiesList;
import com.mcnz.jee.soap.ContentBodyInfo;
import com.mcnz.jee.soap.ContentURLsInfo;
import com.mcnz.jee.soap.ContentURLsList;
import com.mcnz.jee.soap.GetContentRequest;
import com.mcnz.jee.soap.GetContentResponse;


@Endpoint
public class ContentEndpoint {
	// Inject properties directly using @Value
    @Value("${dfc.globalregistry.username}")
    private String username;

    @Value("${dfc.globalregistry.password}")
    private String password;

    @Value("${dfc.globalregistry.repository}")
    private String repoName;
    
    
	@PayloadRoot(namespace = "http://soap.jee.mcnz.com/", localPart ="GetContentRequest")
	@ResponsePayload
	public GetContentResponse getContent(@RequestPayload GetContentRequest request) throws Exception {
		
		String contentID = request.getIdentitiesList().getContentIdentity().getContentID();
		String identityType = request.getIdentitiesList().getContentIdentity().getIdentityType();
		String repositoryName = request.getIdentitiesList().getContentIdentity().getRepositoryName();
		
		/////////DOCUMENTUM SESSION PART //////////////////////////////
		
		IDfSessionManager manager = DfcSessionManager.getSessionManager();
        IDfSession session = null;
        InputStream inputStream = null;
        IDfLoginInfo loginInfo = new DfLoginInfo();
        loginInfo.setUser(username);
        loginInfo.setPassword(password);
        manager.setIdentity(repoName, loginInfo);

        try {
            // Start a session with Documentum
            session = manager.getSession(repositoryName);
            // Check if session is valid
            if (session == null) {
                throw new Exception("Failed to obtain session for repository: " + repositoryName);
            }
            // Fetch the document using the object ID
            IDfId id = new DfId(contentID);
            IDfSysObject documentObject = (IDfSysObject) session.getObject(id);
            // Check if document object is null
            if (documentObject == null) {
                throw new Exception("Document with ID " + contentID+ " not found in repository: " + repositoryName);
            }

            // Check content type and retrieve the content
            inputStream = documentObject.getContent();
            // If inputStream is null, the document may not have content
            if (inputStream == null) {
                throw new Exception("Document with ID " + contentID + " has no content.");
            }
            // Convert the InputStream to Base64
            String base64Content = convertInputStreamToBase64(inputStream);
            // Get the format of the document
            String documentType = documentObject.getContentType();
		
		//////////////////////////////////////////////////////////////
		
		ContentBodyInfo contentBodyInfo = new ContentBodyInfo();
        contentBodyInfo.setRepositoryName(repositoryName);
        contentBodyInfo.setContent(base64Content);
        contentBodyInfo.setType(documentType);
        
        ContentBodiesList contentBodiesList = new ContentBodiesList();
        contentBodiesList.setContentBodyInfo(contentBodyInfo);
        
        
        ContentURLsInfo contentURLsInfo = new ContentURLsInfo();
       
       contentURLsInfo.setContentBodiesList(contentBodiesList);
        
        ContentURLsList contentURLsList = new ContentURLsList();
        contentURLsList.setContentURLsInfo(contentURLsInfo);


        
        GetContentResponse response = new GetContentResponse();
		response.setContentURLsList(contentURLsList);
		return response;
		
	}
        /*
		 * catch (DfException e) { throw new
		 * Exception("Error interacting with Documentum: " + e.getMessage(), e);
		 * 
		 * }
		 */
        catch (Exception e) {	 
            throw new Exception("Error retrieving document: " + e.getMessage(), e);
        } finally {
            // Close the input stream and release the session
            if (inputStream != null) {
                inputStream.close();
            }
            if (session != null) {
                manager.release(session);
            }
        }
	}
        
        
     // Method to convert InputStream to Base64 string
        private String convertInputStreamToBase64(InputStream inputStream) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read from inputStream into buffer and write into byteArrayOutputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            // Encode the byte array to Base64
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        }
}
