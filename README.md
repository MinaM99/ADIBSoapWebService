LINK FOR CREATING A SPRING BOOT SOAP PROJECT USING ONLY A DESIRED XSD FILE : 
https://www.youtube.com/watch?v=MIDEXcU-Bmg&ab_channel=CameronMcKenzie

ENDPOINT To test with (POSTMAN/SOAPUI): 
http://localhost:8080/ws

REQUEST BODY :
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soap.jee.mcnz.com/">
   <soapenv:Header/>
   <soapenv:Body>
         <soap:IdentitiesList>
            <soap:ContentIdentity>
               <soap:ContentID>?</soap:ContentID>
               <soap:IdentityType>?</soap:IdentityType>
               <soap:RepositoryName>?</soap:RepositoryName>
            </soap:ContentIdentity>
         </soap:IdentitiesList>
   </soapenv:Body>
</soapenv:Envelope>

