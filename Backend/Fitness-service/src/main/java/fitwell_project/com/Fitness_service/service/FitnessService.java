package fitwell_project.com.Fitness_service.service;

import fitwell_project.com.Fitness_service.dto.UserDto;
import fitwell_project.com.Fitness_service.feign.UserClient;
import fitwell_project.com.Fitness_service.model.ExerciseLog;
import fitwell_project.com.Fitness_service.repository.ExerciseLogRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.Timestamp;


import java.util.HashMap;
import java.util.Map;

@Service
public class FitnessService {

    @Autowired
    private ExerciseLogRepository exerciseLogRepository;

    @Autowired
    private UserClient userClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final RestTemplate restTemplate;

    public FitnessService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ExerciseLog logExercise(int userId, String activity, int duration) {
        // Step 1: Fetch user details from the User microservice
        UserDto user = userClient.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Step 2: Prepare the prompt for Gemini API
        String prompt = String.format("Based on the following data calculate calories burned. User's height: %s cm, weight: %s kg, age: %s years, sex: %s, activity: %s, duration: %s minutes.",
                user.getHeight(), user.getWeight(), user.getAge(), user.getSex(), activity, duration);

        // Step 3: Send the prompt to Gemini API and get calories burned
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + geminiApiKey); // Add your API key here

        Map<String, String> body = new HashMap<>();
        body.put("prompt", prompt);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(geminiApiUrl, request, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error fetching data from Gemini API");
        }

        // Assuming Gemini API returns a JSON object with a "caloriesBurned" field
        String responseBody = response.getBody();
        float caloriesBurned = extractCaloriesFromResponse(responseBody);


        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setUserId(userId);
        exerciseLog.setActivity(activity);
        exerciseLog.setDuration(duration);
        exerciseLog.setCaloriesBurned(caloriesBurned);
        exerciseLog.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        return exerciseLogRepository.save(exerciseLog);
    }

    // Helper method to extract calories burned from Gemini API response
    private float extractCaloriesFromResponse(String responseBody) {
        // Parse the response and extract the caloriesBurned value
        // This is a placeholder. Actual implementation depends on the API response structure.
        // Assuming response is in JSON format like {"caloriesBurned": 200}
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getFloat("caloriesBurned");
    }
}
