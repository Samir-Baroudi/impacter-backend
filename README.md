# Impacter Backend

### Overview
The Impacter Backend service provides a basic API to handle posts.

###Techniques
The Following techniques and frameworks are used in this Project:

- Java SDK, Version: 11
- Spring Boot, Version: 2.2.4.RELEASE
- Maven, Version: 4.0.0
- Embedded Tomcat 9.0, Servlet Version: 4.0
- Project-lombok, Version: 1.18.10 
- Mockito Testing framework, Version: 1.10.19
- PostgreSQL Connector, Version: 42.2.10

### API
The service provides a public HTTP-based RESTful API.

The API provides the following functionality:

- Get all posts: ```/posts/all```
- Get all posts for a specific Impacter: ```/posts/impacters/{IMPACTER_ID}/all```
- Get a post by impacter: ```/posts/{POST_ID}/impacters/{IMPACTER_ID}```
- Get a post by id: ```/posts/{POST_ID}```
- Update a post: ```/posts/{POST_ID}/update```
- Delete a post: ```/posts/{POST_ID}/delete```
    
## Quick Start

The easiest way to get the impacter-backend up and running is by running the "com/milkywire/impacterservice/Application.java" file that includes a Main method or run the maven "spring-boot:run" command.
 
 - Clone imapcter-backend repository
 - Open the project folder in your terminal
 - Run: ``` mvn spring-boot:run ```
 
 ## Improvements
 
 - Protect the "Open" API by adding Spring Security rules and enabling User-login functionality
 - Implement HTTP argument validation  
 - Implement an Error handling for REST exceptions
 - Make some "Post" fields type-safe by implementing enums to reflect the type-constraints in the Database
 - Implement Integration test suits on an In-memory DB or a separate PostgreSQL test database    
