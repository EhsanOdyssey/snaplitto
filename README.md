<h1 align="center">
    <br>
    <a href="https://snapp.ir">
        <img src="https://github.com/EhsanOdyssey/snaplitto/blob/master/docs/img/snapp.png" alt="Snapp!">
    </a>
    <br>
    Snaplitto REST APIs
    <br>
</h1>
<h3 align="center">Share bills with your friends</h3>
<h4 align="center">An RESTful web service that splits bills (such as trip and restaurants) and shares them with your friends written in Spring boot and MySQL database.</h4>
<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-red.svg">
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.5.1-magenta.svg" />
    </a>
    <a alt="MySQL">
        <img src="https://img.shields.io/badge/MySQL-v8.0.23-white.svg" />
    </a>
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v20-cyan.svg" />
    </a>
    <a alt="Swagger">
        <img src="https://img.shields.io/badge/Swagger-v3-blue.svg" />
    </a>
</p>

## Table of Contents ##
1. [Philosophy](#Philosophy)
2. [Application](#Application)
3. [Technology](#Technology)
4. [Application Structure](#Application-Structure)
5. [Running the server locally](#Running-the-server-locally)
6. [Running the server as Docker Container](#Running-the-server-as-Docker-Container)
7. [API Documentation](#API-Documentation)
8. [Contributors](#Contributors)

## Philosophy ##
This application work with Spring boot and MySQL db. A sample application to sharing bills with your friend.

## Application ##
The application is a web service RESTful apis for splitting bills with friends that joined to the group by the user. Then user with notify friends to pay their expenses. After all user paid their expenses, the status of the bill will be closed.
The complete systems has two important actors:

1. Admin User
2. End User

The _Admin user_ can access to user resource apis to create all _End user_ for consuming by the application. Note that this part should be moved to another microservice (User Management) and just get service from it.
The _End user_ can access expense's group and expense resources to do what is necessary actions.

## Technology ##
Following libraries were used during the development of this application:

- **Spring Boot** - Server side framework
- **Docker** - Containerizing framework
- **MySQL** - Database
- **Swagger** - API documentation
- **JWT** - Authentication mechanism for REST APIs

## Application Structure ##
Spring Boot is a framework that make our life very easy because we don't need to choose the versions of different dependencies based on the version of Spring framework, all taken care by the Spring boot.

**_Controller_**
This layer is related to all APIs that this service is produces for the UI service. This layer present in the **_controller_** package. For now, I have used **_v1_** package for the first version of the APIs. The request classes are located under the **_request_** package. The request classes converted to DTOs to prepare the service layer data. 

**_Models & DTOs_**
All the models are under **_model_** package and their DTOs are under the **_dto_** package. The Models used for DAO layer and DTOs let to transfer only the data that we need to share with the user. The mapping of the models to the DTOs handled using ModelMapper utility.

**_DAOs_**
The data access objects (DAOs) are present in the **_repository_** package. They are classes that use CrudRepository interface to helping the service layer to CRUD on the data from MySQL database. The service layer is defined under the **_service_** package.

**_Security_**
The security setting are under the **_config_** package and **_security_** package. For the REST APIs I have used JWT token based authentication mechanism that validate token by OAuth server by introspect endpoint and get client credential token from OAuth server to communicate with other microservices.

**_Exception-Handling_**
I have used ControllerAdvice feature of the Spring framework to handle the Runtime exceptions that is presenting under **_exception_** package. All exceptions that thrown by the service layer is wrapped by the **_ServiceException_** class and then handled by controller advice for presenting in UI in single view of exceptions.

**_Custom-Validation-Constraints_**
The @Phone custom validation constraint used for validate mobile number on username field that used the Google's libphonenumber library for validation. The related classes are under **_validator_** package.

## Running the server locally ##
To be able to run this app you will need to do the below:

1. If you have MySQL database on your computer
2. To run the Spring Boot app from command line in a Terminal window, run the below command:

```
mvn clean package -P dev
```

```
java -jar target/snaplitto.jar
```

You can also use Maven plugin to run the app by the below command:

```
mvn clean -P dev spring-boot:run
```

3. You can access to the APIs by the below base URL in **_dev_** profile

http://localhost:8020

## Running the server as Docker Container ##
Another alternative to run the application is to use the docker-compose.yml file:

```
docker-compose build
```

Command to run the application :

```
docker-compose up
```

## API Documentation ##
The service APIs documents are available in a readable manner by swagger that I have used springdoc-openapi library. You can open the same inside a browser at the following url:

http://localhost:8020/swagger-ui.html

## Contributors ##
[AmirEhsan Shahmirzaloo](https://linkedin.com/in/ehsan-shahmirzaloo)
