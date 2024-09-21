package fitwell_project.com.DietService.service;


import fitwell_project.com.DietService.exception.ApiRequestException;
import fitwell_project.com.DietService.exception.DietLogNotFoundException;
import fitwell_project.com.DietService.feign.UserClient;
import fitwell_project.com.DietService.feign.UserDto;
import fitwell_project.com.DietService.model.Diet;
import fitwell_project.com.DietService.repository.DietRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import  java.sql.Timestamp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DietService {


    @Autowired
    private DietRepository dietRepository;

    @Value("${calorie.api.url}")
    private String calorieApiUrl;

    @Value("${calorie.api.key}")
    private String calorieApiKey;

    @Autowired
    private UserClient userClient;

    private final RestTemplate restTemplate;

    public DietService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Diet logDiet(int userId, String dietDescription) {
        // Step 1: Fetch calorie data from API
        float calories = fetchCaloriesFromApi(dietDescription);

        // Step 2: Save the diet log
        Diet dietLog = new Diet();
        dietLog.setUserId(userId);
        dietLog.setDiet(dietDescription);
        dietLog.setCalories((int) calories);
        dietLog.setCreatedAt(Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime()));

        return dietRepository.save(dietLog);
    }

    // Helper method to fetch calories from API
    private float fetchCaloriesFromApi(String query) {
        try {
            // Build the API URL with the query
            String url = String.format("%s?query=%s", calorieApiUrl, query);

            // Prepare the request headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", calorieApiKey);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Send the request to the API
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ApiRequestException("Error fetching data from Calorie API");
            }

            // Extract calories from the response
            return extractCaloriesFromResponse(response.getBody());

        } catch (Exception e) {
            throw new ApiRequestException("Error fetching or parsing calorie data", e);
        }
    }

    // Helper method to extract calories from API response
    private float extractCaloriesFromResponse(String responseBody) {
        try {
            // Parse the response body into a JSON object
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray items = jsonResponse.getJSONArray("items");

            // Initialize total calories
            float totalCalories = 0.0f;

            // Iterate over each item in the array and sum up the calories
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                totalCalories += item.getFloat("calories");
            }


            return totalCalories;

        } catch (Exception e) {
            // Handle JSON parsing errors or unexpected structure
            throw new ApiRequestException("Error parsing calorie data from API response", e);
        }
    }



    public Diet findLogById(int logId) {
        Optional<Diet> log = dietRepository.findById(logId);
        if (log.isPresent()) {
            return log.get();
        } else {
            throw new DietLogNotFoundException("Diet Log not found for id: " + logId);
        }
    }

    public List<Diet> findLogsByUserId(int userId) {
        return dietRepository.findByUserId(userId);
    }


    public float calculateOptimumCalorieIntake(UserDto user) {
        float bmr;
        if (user.getGender().equalsIgnoreCase("male")) {
            bmr = 88.362f + (13.397f * user.getWeight()) + (4.799f * user.getHeight()) - (5.677f * user.getAge());
        } else {
            bmr = 447.593f + (9.247f * user.getWeight()) + (3.098f * user.getHeight()) - (4.330f * user.getAge());
        }

        float activityFactor;
        switch (user.getActivityLevel().toLowerCase()) {
            case "sedentary":
                activityFactor = 1.2f;
                break;
            case "lightly active":
                activityFactor = 1.375f;
                break;
            case "moderately active":
                activityFactor = 1.55f;
                break;
            case "very active":
                activityFactor = 1.725f;
                break;
            case "super active":
                activityFactor = 1.9f;
                break;
            default:
                activityFactor = 1.2f;
        }
        return bmr * activityFactor;
    }

    public float calculateDietScore(int userId) {
        UserDto user = userClient.getUserById(userId);

        List<Diet> diets = findLogsByUserIdForToday(userId);

        Double calorieInTake = diets.stream()
                .mapToDouble(Diet::getCalories)
                .sum();

        float optimalCalories = calculateOptimumCalorieIntake(user);

        float dietscore = 25 - Math.min(25, Math.abs( (calorieInTake.floatValue()) - optimalCalories) / 100);
        if (dietscore < 0) dietscore = 0;
        return dietscore;
    }

    public List<Diet> findAllLogs(){
        return dietRepository.findAll();
    }

    public List<Diet> findLogsByUserIdForToday(int userId) {
        LocalDate today = LocalDate.now();
        return dietRepository.findByUserId(userId).stream()
                .filter(log -> log.getCreatedAt().toLocalDateTime().toLocalDate().isEqual(today))
                .collect(Collectors.toList());
    }


}
