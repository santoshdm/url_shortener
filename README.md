# url_shortener

## Tech used
1. Java 11  
2. Maven - for build automation  
3. OpenAPI spring generator - for clean boilerplate code generation  

### Project Structure
Maven project with, 
1. Open api spec file placed inside project in directory named __spec__
2. Uml diagrams for same is placed under directory named __docs__  
    a. Class Diagram  
    b. Sequence Diagram

### Build and Run Instruction
1, Run __mvnw clean install__  
2, Then run application jar or app directly from eclipse

### API Details

1. API provides end point named `http://localhost:8080/short-url`, with POST request.  
2. Request body includes URL to be shortened.  
3. Response will have shortned URL with API generated ID(used by API).  
4. Service uses in memory h2 DB (choose it for ease of development).  
5. OpenAPIGenerator named Spring is used  
    i. As it provides standard structure  
    ii. Helps in scaling the app very easily  
    iii. Faster development - using APICurio one can create end points with good documentation.  
    iv. Helps in maintaining uniformity and good code quality in large applications.

Note: Swagger documentation is added, once app is launched - can be viewed at `http://localhost:8080/v2/api-docs`.  


P.S: 
1. Algorithm used for generation of short url is referred from below URL.
`https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener`  
2. Due to shortage of time, unit test case file is not refactored.
