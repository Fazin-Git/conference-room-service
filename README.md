# conference-room-service
Conference room booking service for company’s internal use.
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
     - Token will be populated to all other API Authorization headers.
- Below API's are available to fulfil the current scope
     - **Swagger** http://localhost:8080/api/swagger-ui/index.html

##### Technologies integrated in the service
- JWT Authentication using Spring security.
- Rate limiter using resiliency4j
- Mock DB test cases
- Swagger;
- Java 17
- Maven
- H2 Database
- Liquibase
### Project Structure
```bash
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
### Project Structure

