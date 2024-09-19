package fitwell_project.com.Fitness_service.controller;

import fitwell_project.com.Fitness_service.dto.ExerciseRequest;
import fitwell_project.com.Fitness_service.model.ExerciseLog;
import fitwell_project.com.Fitness_service.service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitness")
public class FitnessController {

    @Autowired
    private FitnessService fitnessService;

    @PostMapping("/log")
    public ResponseEntity<ExerciseLog> logExercise(@RequestParam int userId, @RequestBody ExerciseRequest exerciseRequest) {
        ExerciseLog exerciseLog = fitnessService.logExercise(userId, exerciseRequest.getActivity(), exerciseRequest.getDuration());
        return new ResponseEntity<>(exerciseLog, HttpStatus.CREATED);
    }

    @GetMapping("/log/{logId}")
    public ResponseEntity<ExerciseLog> getLogById(@PathVariable int logId) {
        ExerciseLog exerciseLog = fitnessService.findLogById(logId);
        return ResponseEntity.ok(exerciseLog);
    }

    @GetMapping("/logs/user/{userId}")
    public ResponseEntity<List<ExerciseLog>> getLogsByUserId(@PathVariable int userId) {
        List<ExerciseLog> logs = fitnessService.findLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/score/{userId}")
    public ResponseEntity<Float> getFitnessScore(@PathVariable int userId) {
        float score = fitnessService.calculateFitnessScore(userId);
        return ResponseEntity.ok(score);
    }


}

