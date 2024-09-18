
To test this in Postman, you will need to:

Create a user in the User service.
Log an exercise in the Fitness service.
Here’s how to proceed:

1. Create a User in the User Service
Assuming the User service is running on localhost:8080, you can create a user by making a POST request in Postman.

Endpoint:
bash
Copy code
POST http://localhost:8080/users
Request Body (JSON):
json
Copy code
{
    "name": "John Doe",
    "sex": "male",
    "age": 30,
    "username": "johndoe",
    "password": "password123",
    "email": "johndoe@example.com",
    "height": 180,   // Height in cm
    "weight": 75,    // Weight in kg
    "bmi": 23.1,
    "activityLevel": "moderate"
}
Once you send this request, it should create a user, and you should receive a response with a user ID, which you will need for the next step.

2. Log an Exercise in the Fitness Service
After creating a user in the User service, you can log an exercise for that user in the Fitness service. Assume the Fitness service is running on localhost:8081.

Endpoint:
bash
Copy code
POST http://localhost:8081/fitness/log?userId={userId}
Replace {userId} with the actual id you received from the response of the User service (e.g., 1).

Request Body (JSON):
json
Copy code
{
    "activity": "Running",
    "duration": 30   // Duration in minutes
}
Example Request:
bash
Copy code
POST http://localhost:8081/fitness/log?userId=1
After sending this request, the exercise log will be created for the user, and the response will include the details of the logged exercise, including caloriesBurned (fetched from the Gemini API) and the createdAt timestamp.
