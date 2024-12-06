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

## 📜 Generated WSDL File
The WSDL file for this SOAP service is automatically generated and can be accessed at:

```plaintext
http://localhost:8080/ws/content.wsdl
```

## 🛠️ Endpoint for Testing

Use the following endpoint to test the SOAP web service with **Postman** or **SOAPUI**:

```plaintext
http://localhost:8080/ws
```

## 📨 Sample Request Body
Below is a sample SOAP request body that you can use to test the web service:

```plaintext
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soap.jee.mcnz.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:GetContentRequest>
         <soap:IdentitiesList>
            <soap:ContentIdentity>
               <soap:ContentID>?????</soap:ContentID>
               <soap:IdentityType>?????</soap:IdentityType>
               <soap:RepositoryName>?????</soap:RepositoryName>
            </soap:ContentIdentity>
         </soap:IdentitiesList>
      </soap:GetContentRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## 📝 Notes
Replace placeholders in the sample request body with actual values:

- `<soap:ContentID>`: Add the desired content ID.
-``<soap:IdentityType>`: Specify the identity type.
- `<soap:RepositoryName>`: Provide the repository name.

Make sure your Spring Boot application is running on localhost:8080 before testing.

The video tutorial provides step-by-step guidance for creating this project, including:

Generating Java classes from the XSD file.
Configuring the application.properties file.
Setting up the SOAP web service endpoint.


