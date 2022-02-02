# snaplitto
An RESTful web service that splits bills (such as trip and restaurants) to your friends written in Spring boot.
<h1 align="center">
  <br>
  Snaplitto
  <br>
</h1>
<h4 align="center">Snaplitto - Sharing bills with friends with Spring boot and MYSQL database.</h4>
<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-orange.svg">
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.5.1-brightgreen.svg" />
    </a>
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v19-yellowgreen.svg" />
    </a>
</p>

## Table of Contents ##
1. [Philosophy](#Philosophy)
2. [Application](#Application)
3. [Technology](#Technology)
4. [Application Structure](#Application-Structure)

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
