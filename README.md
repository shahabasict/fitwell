# FitWell: Overall Health Monitor

## Overview

FitWell is a comprehensive health monitoring application that helps you log your diet, fitness activities, and daily journals. The application analyzes these inputs to provide an overall health score and a Body Mass Index (BMI) score.

The application is built using Angular 18 for the frontend and Spring Boot 6 for the backend. It uses MySQL as the database server and implements security using JWT authentication. The services are connected through a Gateway service to ensure smooth and secure communication.

## Features

-Diet Logging: Track your daily dietary intake.

-Fitness Activity Logging: Log your physical activities and exercises.

-Daily Journals: Maintain a journal to log your daily thoughts and activities.

-Health Analysis: Get an overall health score based on your diet, fitness activities, and journals.

-BMI Calculation: Calculate and display your BMI based on the logged data.

## Prerequisites

-Java: Ensure that Java 21 is installed on your system.

-Angular: Ensure that Angular version 18 is installed on your system.

-MySQL Server: Install MySQL server and set up the database. Update the SQL username and password according to your system configuration.

## Installation

Backend (Spring Boot)

### Clone the repository:

git clone https://github.com/your-username/fitwell.git

cd fitwell/backend

### Update MySQL configuration: 
Update the application.yaml file with your MySQL username and password.

yaml

Copy code

spring:
  
  datasource:

  url: jdbc:mysql://localhost:3306/fitwell
    
username: your-username

password: your-password

### Build and run the Spring Boot application:

### bash
Copy code
./mvnw clean install
./mvnw spring-boot:run
Frontend (Angular)
Navigate to the frontend directory:

### bash
Copy code
cd fitwell/frontend
Install dependencies:

### bash
Copy code
npm install
Run the Angular application:

### bash
Copy code
ng serve
## Usage
Access the application: Open your browser and navigate to http://localhost:4200.

## Log in: Use the credentials provided during the setup to log into the application.

## Start logging:

Diet: Navigate to the Diet section to log your daily dietary intake.
Fitness: Navigate to the Fitness section to log your physical activities.
Journal: Navigate to the Journal section to maintain your daily journal.
View Health Score: Navigate to the Dashboard to view your overall health score and BMI score.

## Contributing
We welcome contributions! Please read our Contributing Guidelines for more details.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
If you have any questions or suggestions, feel free to open an issue or contact us at Shahabas.Aman@ust.com or soumya.yechuru@ust.com

## Happy Monitoring with FitWell!
