package fitwell_project.com.Fitness_service.service;

import fitwell_project.com.Fitness_service.dto.UserDto;
import fitwell_project.com.Fitness_service.exception.ExerciseLogNotFoundException;
import fitwell_project.com.Fitness_service.exception.NoValidDataReturnedException;
import fitwell_project.com.Fitness_service.exception.UserNotFoundException;
import fitwell_project.com.Fitness_service.feign.UserClient;
import fitwell_project.com.Fitness_service.model.ExerciseLog;
import fitwell_project.com.Fitness_service.repository.ExerciseLogRepository;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.Timestamp;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FitnessService {

    @Autowired
    private ExerciseLogRepository exerciseLogRepository;

    @Autowired
    private UserClient userClient;

    @Value("${ninja.api.url}")
    private String ninjaApiUrl;

    @Value("${ninja.api.key}")
    private String ninjaApiKey;

    private final RestTemplate restTemplate;

    public FitnessService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Float logExercise(int userId, String activity, int duration) {
        // Step 1: Fetch user details from the User microservice
        UserDto user = userClient.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Step 2: Send request to Ninja Calories Burned API with only the activity parameter
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", ninjaApiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        // Build the API URL with just the activity parameter
        String url = String.format("%s?activity=%s", ninjaApiUrl, activity);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new UserNotFoundException("Error fetching data from Ninja API");
        }

        // Step 3: Extract calorie data and calculate based on actual duration
        String responseBody = response.getBody();
        float caloriesPerHour = extractCaloriesPerHourFromResponse(responseBody);
        float totalCaloriesBurned = (caloriesPerHour / 60) * duration; // Convert to calories based on actual duration

        // Step 4: Save the exercise log
        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setUserId(userId);
        exerciseLog.setActivity(activity);
        exerciseLog.setDuration(duration);
        exerciseLog.setCaloriesBurned(totalCaloriesBurned);
        exerciseLog.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        exerciseLogRepository.save(exerciseLog);

        return totalCaloriesBurned;
    }

    // Helper method to extract calories per hour from the Ninja API response
    private float extractCaloriesPerHourFromResponse(String responseBody) {
        JSONArray jsonArray = new JSONArray(responseBody);
        if (jsonArray.length() > 0) {
            JSONObject firstActivity = jsonArray.getJSONObject(0);
            return firstActivity.getFloat("calories_per_hour");
        } else {
            throw new NoValidDataReturnedException("No valid data returned from Ninja API");
        }
    }

    public List<ExerciseLog> findLogsByUserIdForToday(int userId) {
        LocalDate today = LocalDate.now();
        return exerciseLogRepository.findByUserId(userId).stream()
                .filter(log -> log.getCreatedAt().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
    }

    public ExerciseLog findLogById(int logId) {
        Optional<ExerciseLog> log = exerciseLogRepository.findById(logId);
        if (log.isPresent()) {
            return log.get();
        } else {
            throw new ExerciseLogNotFoundException("Exercise Log not found for id: " + logId);
        }
    }

    // Method to find all logs by userId
    public List<ExerciseLog> findLogsByUserId(int userId) {
        return exerciseLogRepository.findByUserId(userId);
    }


    public float calculateFitnessScore(int userId) {
        // Fetch today's logs from the Fitness service
        List<ExerciseLog> logs = findLogsByUserIdForToday(userId);

        // Calculate total calories burned today
        Double totalCaloriesBurned = logs.stream()
                .mapToDouble(ExerciseLog::getCaloriesBurned)
                .sum();

        // Calculate the score based on the calories burned
        if (totalCaloriesBurned >= 300) {
            return 25.0f;
        } else if (totalCaloriesBurned > 0) {
            return (float) ((totalCaloriesBurned / 300) * 25);
        } else {
            return 0.0f;
        }
    }

}