# conference-room-service
Conference room booking service for company’s internal use.
Candidate : Fasin Muhammed
##### Steps to run the application
- Checkout code from https://github.com/Fazin-Git/conference-room-service
- Install Openjdk version 17.0.6
- Maven
- Download postman and import postman collection (API-Collection.json available in project root directory)
- Step 1 - Use Sign-up API with valid Name, Email and Password requirement is below.
     - At least 8 characters
     - Contains at least one digit
     - Contains at least one special character (@, #, $, %, ^, &, +, =, or !)
     - Does not contain whitespace
- Step 2 - Use login API with same email and password.
     - Postman collection already have the scripts to copy the token from response to set the global variable {{token}}
     - Please create a global variable {{token}} if variable is not created.(Some postman version will not import global variables.)
     - Token will be populated to all other API Authorization headers.
- Below API's are available to fulfil the current scope
     - **Swagger** http://localhost:8080/api/swagger-ui/index.html
     - **H2 Database** (sa/password) - http://localhost:8080/api/h2-console/login.jsp?jsessionid=4a94a0d8abc10686f675278a2184fa59

##### Technologies integrated in the service
- JWT Authentication using Spring security.
- Rate limiter using resiliency4j
- Mock DB test cases
- Swagger
- Java 17
- Maven
- H2
- Liquibase
### Project Structure
Project is built using **Hexagonal** design pattern.
Domain objects are the lifeblood of an application.They’re pure Java and provide an API for use cases to operate on them.
Because domain objects have no dependencies on other layers of the application, changes in other layers don’t affect them. 
They can evolve free of dependencies. 
This is a prime example of the Single Responsibility Principle (the “S” in “SOLID”)

 - **domain**: Contains the core domain entities and business logic.
 - **inward**: Defines the input port (use case interface).
 - **outward**: Defines the output port (repository interface).
 - **adapter**: Adapters for both inward and outward. In this example, 
   there's a web adapter for handling HTTP requests and an in-memory repository for storing bookings.
```bash![img.png](img.png)
conference-room-service
src
   |-- com/mashreq
   |   |   |   |   |-- conference
   |   |   |   |   |   |-- ConferenceRoomServiceApplication.java
   |   |   |   |   |   |-- adapters
   |   |   |   |   |   |   |-- inbound
   |   |   |   |   |   |   |   |-- BookingControllerAdapter.java
   |   |   |   |   |   |   |-- outbound
   |   |   |   |   |   |   |   |-- persistence
   |   |   |   |   |   |   |   |   |-- BookingRepositoryAdapter.java
   |   |   |   |   |   |-- domain
   |   |   |   |   |   |   |-- model
   |   |   |   |   |   |   |   |-- BookingRequest.java
   |   |   |   |   |   |   |-- service
   |   |   |   |   |   |   |   |-- BookingService.java
   |   |   |   |   |   |   |   |-- impl
   |   |   |   |   |   |   |   |   |-- BookingServiceImpl.java
   |   |   |   |   |   |-- infra
   |   |   |   |   |   |   |-- config
   |   |   |   |   |   |   |   |-- JwtAuthFilter.java
   |   |   |   |   |   |   |-- exceptions
   |   |   |   |   |   |   |   |-- BookingErrorResponse.java
   |   |   |   |   |   |   |-- validator
   |   |   |   |   |   |   |   |-- BookingValidator.java
   |   |   |   |   |   |-- persistence
   |   |   |   |   |   |   |-- entity
   |   |   |   |   |   |   |   |-- BaseEntity.java
   |   |   |   |   |   |   |-- repository
   |   |   |   |   |   |   |   |-- BookingRepository.java
   |   |   |   |   |   |-- ports
   |   |   |   |   |   |   |-- inbound
   |   |   |   |   |   |   |   |-- BookingController.java
   |   |   |   |   |   |   |-- outbound
   |   |   |   |   |   |   |   |-- IBookingRepository.java
```

