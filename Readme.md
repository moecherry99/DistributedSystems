# GRPC Project Distributed Systems

This project is to create interprocess communication protocols between distributed systems. It is to create a password service between interconnected services. The project is done using gRPC methods.

Passwords must be stored in a proper encoding method. This project is to hash and salt passwords so that they do not get broken by encryption methods as the salting generates a new form of the hashed password.

There is a simple Server side application, with a Client connecting to it. There is a Passwords java class for cryptography, and a PasswordServiceImpl class.

## Instructions for Part 1

1. Build the project using Maven in Eclipse.

2. Start up the PasswordServer java program and wait until it is loaded.

3. Start up the PasswordClient java program and enter the User Id and Password when prompted.

4. The password will be automatically hashed and salted. User validation has been applied if password input on second try is false.

## Jar instructions

Change to directory up until \ds and run java -jar PasswordServer1.jar 

