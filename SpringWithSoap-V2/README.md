# Spring Boot SOAP Project

This project demonstrates how to create a Spring Boot SOAP application using a desired XSD file. It also integrates with **Documentum** to fetch content from its repository and return it as a Base64-encoded string. Follow along with the video tutorial linked below to set up the project.

---

## 📺 Tutorial Link for Spring Boot SOAP Web Service Example:
[Spring Boot SOAP Project Using an XSD File - Video Tutorial](https://www.youtube.com/watch?v=MIDEXcU-Bmg&ab_channel=CameronMcKenzie)

---

## 💻 Development Environment
Tools Used:
- Eclipse IDE: Used for project development.
- Maven: For building and managing project dependencies.

---

## 🚀 How to Build and Run the Application in Eclipse
### Build, Clean, Install, and Run the Project

#### 1. Build the Project:
- Right-click on the project in the Eclipse Project Explorer.
- Select **Maven** → **Update Project**.

#### 2. Clean the Project:
- Right-click on the project.
- Select **Run As** → **Maven clean**.

#### 3. Install the Project:
- Right-click on the project.
- Select **Run As** → **Maven install**.

#### 4. Run the Application:
- Right-click on the project.
- Select **Run As** → **Spring Boot App**.

#### 5. Test the Application:
Use tools like **Postman** or **SOAPUI** to test the application at `http://localhost:8080/ws`.

## 📜 Generated WSDL Files
The WSDL files for this SOAP service are automatically generated and can be accessed at:

```plaintext
http://localhost:8080/ws/contentretrieve.wsdl
```

## 🛠️ Endpoint for Testing

Use the following endpoint to test the SOAP web service with **Postman** or **SOAPUI**:

```plaintext
http://localhost:8080/ws
```

## 📨 Sample Request Body
Below is a sample SOAP request body that you can use to test the web service, including the required authentication header:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soap.jee.mcnz.com/">
   <soapenv:Header>
      <soap:AuthenticationHeader>
         <soap:username>???</soap:username>
         <soap:password>???</soap:password>
      </soap:AuthenticationHeader>
   </soapenv:Header>
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
```

## 🔐 Authentication
This version of the project requires authentication for SOAP requests. You must include a WS-Security header in your SOAP envelope, as shown in the sample request above. Replace `your-username` and `your-password` with valid credentials. The application will validate these credentials before processing the request.

## 🆕 What’s New in This Branch/Version?
- **Authentication Added:** SOAP requests now require a WS-Security UsernameToken in the header for authentication.
- **Enhanced Security:** Only authenticated users can access the SOAP endpoints.
- **Other Improvements:** (Add any other changes here if applicable, such as refactoring, bug fixes, or integration updates.)

## 📝 Notes
Replace placeholders in the sample request body with actual values:

- `<soap:ContentID>`: Add the desired content ID.
- `<soap:IdentityType>`: Specify the identity type.
- `<soap:RepositoryName>`: Provide the repository name.

Make sure your Spring Boot application is running on localhost:8080 before testing.
- Don't forget to change the system path for most of the dependencies in the pom.xml.

The video tutorial provides step-by-step guidance for creating this project, including:

Generating Java classes from the XSD file.  
Configuring the application.properties file.  
Setting up the SOAP web service endpoint.
