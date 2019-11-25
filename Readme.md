# GRPC Project Distributed Systems Part 1

## By Alex Cherry, G00347106

This project is to create interprocess communication protocols between distributed systems. It is to create a password service between interconnected services. The project is done using gRPC methods.

Passwords must be stored in a proper encoding method. This project is to hash and salt passwords so that they do not get broken by encryption methods as the salting generates a new form of the hashed password.

There is a simple Server side application, with a Client connecting to it. There is a Passwords java class for cryptography, and a PasswordServiceImpl class.

### Instructions for Part 1

1. Build the project using Maven in Eclipse.

2. Start up the PasswordServer java program and wait until it is loaded.

3. Start up the PasswordClient java program and enter the User Id and Password when prompted.

4. The password will be automatically hashed and salted. User validation has been applied if password input on second try is false.

### Jar instructions

Change to directory up until \ds and run java -jar PasswordServer1.jar 

### References for part 1

GRPC basic setup of client and server
https://www.grpc.io/docs/tutorials/basic/java/

https://wiki.eclipse.org/Tutorial:_Using_Google_RPC/ProtocolBuffers_for_Remote_Services

Password Cryptography
https://gist.github.com/john-french/9c94d88f34b2a4ccbe55af6afb083674

GRPC helper code 
https://github.com/john-french/distributed-systems-labs/tree/master/grpc-async-inventory


# GRPC Project Distributed Systems Part 2

The second part of the project is to design a REST API for a User Account Service using OpenAPI and SwaggerHub. The User Account Service must contain an API in Java that is implemented using the Dropwizard microservice framework.

### Instructions for part 2

1. Build the Maven project in a terminal by using "mvn package".

2. Run program with java -jar target\artist-api-dropwizard-soln-1.0-SNAPSHOT.jar server userApiConfig.yaml.

3. Open up localhost:9000/users to view Json data.

4. (To be edited).

### References for part 2
Dropwizard microservice framework reference link
https://www.sumologic.com/blog/dropwizard-java-microservices/

Basic Dropwizard setup
https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/

Basic API creation on swaggerhub
https://swagger.io/blog/api-development/how-to-build-an-api/ 

Dropwizard setup helper code
https://github.com/john-french/artistAPI-dropwizard-soln

