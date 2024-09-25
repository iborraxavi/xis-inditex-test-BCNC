# Prices microservice
> Inditex prices microservice

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Building the project](#building-the-project)
* [Running the application locally](#running-the-application-locally)


## General Information
- Provide information of prices.
- APIs:
    - /prices?applicationDate=2020-06-14T10:00:00Z&productId=35455&brandId=1


## Technologies Used
- Spring Boot 3.3.4


## Building the project
You will need:

*	Java JDK 21 or higher
*	Maven 3.9.0 or higher
*	Git

Clone the project and use Maven to build the server

```shell
mvn clean install
```


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
