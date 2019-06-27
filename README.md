# spring-boot-rest-tdd-telecom-demo
Test driven development creating a simple API with spring boot

Import the project into spring tools suite, right click on the project -> maven -> update project 

The project makes use of the in-memory database 'com.h2database' for testing.

### Run the tests
navigate to your-workspace\Telecom-demo\src\test\java\com\dale\test\PhoneNumberTestSuite.java

Right click and run as JUnit test

### Run the api
Right click on the project -> run as -> spring boot project

Runs on localhost:8080

### Run the postman API
Open Postman and import 'TelecomDemo.postman_collection.json'. Now you can:

You can:


PUT -> http://localhost:8080/api/phoneNumber - adds or updates a number

PUT -> http://localhost:8080/api/phoneNumber/1/activate - activates a 

GET -> http://localhost:8080/api/phoneNumber - get all phone numbers

GET -> http://localhost:8080/api/phoneNumber/customer/1 - get customer numbers

NOTE:  The orginal task was to update the number hence the 'http://localhost:8080/api/phoneNumber/1/activate' request. A post should be used to create a new one.


###
View API view Swagger
http://localhost:8080/swagger-ui.html
