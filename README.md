# FX Deal Details Store

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Directory Structure](#project-directory-structure)
- [Getting Started](#getting-started)

## Overview

This project is about building a solution that takes foreign exchange (FX) deal details and stores them in a database for analysis. The core task is to ensure that deal information is collected accurately and saved reliably.

## Features

- **data validation**
- **data persistence**
- **error handling**


## Technologies Used

The project uses a variety of technologies to ensure robust functionality, efficient development, and comprehensive testing. Below is a list of the key technologies used:

- **Spring Boot**: Provides a framework for building standalone Spring applications with minimal configuration.

- **Maven**: A build automation tool used to manage project dependencies.

- **PostgreSQL**: An open-source relational database management system. It serves as the primary database for this project.

- **MapStruct**: A Java annotation processor that simplifies the mapping between Java objects.

- **Lombok**: A Java library that helps reduce boilerplate code. It also includes support for **Slf4j** for streamlined logging.

- **Mockito**: A popular mocking framework for unit testing in Java.

- **JUnit**: The primary testing framework for Java applications. It's used to write and run unit tests.

- **Swagger**: A tool for generating and documenting RESTful APIs. It helps create interactive API documentation, allowing users to explore and test API endpoints.

- **Docker**: A platform for building, packaging, and deploying applications as containers.


## Project Directory Structure

```bash
project-root/
├───src
  ├───main
   │   ├───java
   │   │   └───com
   │   │       └───ps
   │   │           └───warehouse
   │   │               ├───controllers
   │   │               ├───dtos
   │   │               ├───entities
   │   │               ├───mappers
   │   │               ├───repositories
   │   │               ├───rest_apis
   │   │               ├───services
   │   │               ├───utils
   │   │               └───validation
   │   │                   ├───annotations
   │   │                   ├───config
   │   │                   ├───errorhandling
   │   │                   ├───service
   │   │                   └───validators
   │   └───resources
   │       ├───static
   │       └───templates
   └───test
       └───java
           └───com
               └───ps
                   └───warehouse
                       ├───service
                       └───validation

```


## Getting Started

There are two ways to run the application: using Docker Compose directly or with a Makefile.

### First Way
To run the application using Docker, follow these steps:


1. **Install Docker**:
   If you don't have Docker installed on your machine, download and install it from the [official Docker website](https://www.docker.com/get-started), for linux, you typically add Docker's repository to your package manager, then update and install Docker from that repository. This installation generally includes Docker Engine, Docker CLI, and other components, Docker compose CLI usually requires separate installation.

2. **Clone the Project**:
   Use `git` to clone the project's repository to your local machine:
   ```bash
   git clone <repository-url>
   ```
   
3. **Navigate to the Project Directory**:
   After cloning, navigate to the project's root directory:
   ```bash
   cd <project-directory>
   ```
   Replace <project-directory> with the correct name of your project folder.

4. **Run Docker Compose**:
      Use the following command to build and start all the services required for the application. If you're not on Windows, be sure to update the volume path in the Docker Compose file to match your operating system.:
```bash
docker-compose up --build
```

5. **Swagger**:
   Now You can access the Swagger API documentation at http://localhost:8080/swagger-ui/index.html#/.

### Second Way
To run the application using Makefile, follow these steps:

1. **Ensure Docker is Installed**: 
Ensure Docker Desktop or Docker Engine is installed and running. If not, follow the installation instructions in the first section of this guide.

2. **Update the Environment File**: 
The Makefile includes a target that creates or updates an environment file (`.env`) with required variables. To ensure the correct paths are set, execute:

```bash
make write-env
```

3. **Build and Run the Application**:
   Use make all to build the Docker image, update the .env file, and start the application with Docker Compose:

```bash
make all
```

4. **Access Swagger**:
   Once the application is running, you can access the Swagger API documentation at: http://localhost:8080/swagger-ui/index.html , To stop all running services, use:
```
make stop
```

Once all the services are up and running, you can connect to the server to start creating deal details