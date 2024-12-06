# Spring Boot SOAP Project

This project demonstrates how to create a Spring Boot SOAP application using a desired XSD file. It also integrates with **Documentum** to fetch content from its repository and return it as a Base64-encoded string. Follow along with the video tutorial linked below to set up the project.

---

## 📺 Tutorial Link
[Spring Boot SOAP Project Using an XSD File - Video Tutorial](https://www.youtube.com/watch?v=MIDEXcU-Bmg&ab_channel=CameronMcKenzie)

---
## 🚀 How to Build and Run the Application in Eclipse
Build the Project:

Right-click on the project in the Eclipse Project Explorer.
Select Maven -> Update Project.
Clean the Project:

Right-click on the project.
Select Run As -> Maven clean.
Install the Project:

Right-click on the project.
Select Run As -> Maven install.
Run the Application:

Right-click on the project.
Select Run As -> Spring Boot App.
Test the application using tools like Postman or SOAPUI.

## 🛠️ Endpoint for Testing

Use the following endpoint to test the SOAP web service with **Postman** or **SOAPUI**:

```plaintext
http://localhost:8080/ws

## 📜 Generated WSDL File
The WSDL file for this SOAP service is automatically generated and can be accessed at:
http://localhost:8080/ws/content.wsdl

## 💻 Development Environment
Tools Used:
Eclipse IDE: Used for project development.
Maven: For building and managing project dependencies.

## 📨 Sample Request Body
Below is a sample SOAP request body that you can use to test the web service:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soap.jee.mcnz.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:IdentitiesList>
         <soap:ContentIdentity>
            <soap:ContentID>?????</soap:ContentID>
            <soap:IdentityType>?????</soap:IdentityType>
            <soap:RepositoryName>?????</soap:RepositoryName>
         </soap:ContentIdentity>
      </soap:IdentitiesList>
   </soapenv:Body>
</soapenv:Envelope>

## 📝 Notes
Replace placeholders in the sample request body with actual values:

<soap:ContentID>: Add the desired content ID.
<soap:IdentityType>: Specify the identity type.
<soap:RepositoryName>: Provide the repository name.
Make sure your Spring Boot application is running on localhost:8080 before testing.

The video tutorial provides step-by-step guidance for creating this project, including:

Generating Java classes from the XSD file.
Configuring the application.properties file.
Setting up the SOAP web service endpoint.
