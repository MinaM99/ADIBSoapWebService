package com.mcnz.spring.soap;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.*;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import com.documentum.fc.client.*;
import com.documentum.fc.common.*;
import com.emc.documentum.fs.rt.context.DfcSessionManager;
import com.mcnz.jee.soap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Endpoint
public class ContentEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ContentEndpoint.class);

    @Value("${dfc.globalregistry.repository}")
    private String repoName;

    private AuthenticationHeader extractAuthHeader(MessageContext messageContext) {
        SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
        SoapHeader soapHeader = soapMessage.getSoapHeader();

        if (soapHeader != null) {
            Iterator<SoapHeaderElement> iterator = soapHeader.examineAllHeaderElements();
            while (iterator.hasNext()) {
                SoapHeaderElement headerElement = iterator.next();
                if ("AuthenticationHeader".equals(headerElement.getName().getLocalPart())) {
                    try {
                        DOMResult result = new DOMResult();
                        TransformerFactory.newInstance().newTransformer().transform(headerElement.getSource(), result);
                        Node node = result.getNode();
                        NodeList children = node.getFirstChild().getChildNodes();

                        AuthenticationHeader header = new AuthenticationHeader();
                        for (int i = 0; i < children.getLength(); i++) {
                            Node child = children.item(i);
                            if (child.getNodeType() == Node.ELEMENT_NODE) {
                                if ("username".equals(child.getLocalName())) {
                                    header.setUsername(child.getTextContent());
                                } else if ("password".equals(child.getLocalName())) {
                                    header.setPassword(child.getTextContent());
                                }
                            }
                        }
                        return header;
                    } catch (Exception e) {
                        // Handle transformation error
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @PayloadRoot(namespace = "http://soap.jee.mcnz.com/", localPart = "IdentitiesList")
    @ResponsePayload
    public ContentURLsList getContent(
            @RequestPayload IdentitiesList request,
            MessageContext messageContext) throws Exception {

        logger.info("getContent endpoint called");
        AuthenticationHeader auth = extractAuthHeader(messageContext);
        if (auth == null || auth.getUsername() == null || auth.getPassword() == null) {
            logger.error("Authentication credentials not provided in SOAP header");
            throw new Exception("Authentication credentials not provided in SOAP header");
        }

        String contentID = request.getContentIdentity().getContentID();
        String repositoryName = request.getContentIdentity().getRepositoryName();
        logger.info("Requested contentID: {} from repository: {}", contentID, repositoryName);

        /////////DOCUMENTUM SESSION PART //////////////////////////////
        IDfSessionManager manager = DfcSessionManager.getSessionManager();
        IDfSession session = null;
        InputStream inputStream = null;
        IDfLoginInfo loginInfo = new DfLoginInfo();
        loginInfo.setUser(auth.getUsername());
        loginInfo.setPassword(auth.getPassword());
        manager.setIdentity(repoName, loginInfo);

        try {
            logger.info("Attempting to start a session with Documentum");
            session = manager.getSession(repositoryName);
            if (session == null) {
                logger.error("Failed to obtain session for repository: {}", repositoryName);
                throw new Exception("Failed to obtain session for repository: " + repositoryName);
            }
            logger.info("Session started successfully");
            IDfId id = new DfId(contentID);
            IDfSysObject documentObject = (IDfSysObject) session.getObject(id);
            if (documentObject == null) {
                logger.error("Document with ID {} not found in repository: {}", contentID, repositoryName);
                throw new Exception("Document with ID " + contentID+ " not found in repository: " + repositoryName);
            }
            logger.info("Document object fetched successfully");
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

        ContentURLsList response = new ContentURLsList();
		response.setContentURLsInfo(contentURLsInfo);
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
