package fitwell_project.com.Fitness_service.controller;

import fitwell_project.com.Fitness_service.dto.ExerciseRequest;
import fitwell_project.com.Fitness_service.exception.AuthorizationException;
import fitwell_project.com.Fitness_service.model.ExerciseLog;
import fitwell_project.com.Fitness_service.service.FitnessService;
import fitwell_project.com.Fitness_service.utils.JwtDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fitness")
public class FitnessController {

    @Autowired
    private JwtDecoderService jwtDecoderService;

    @Autowired
    private FitnessService fitnessService;

    @PostMapping("/log")
    public ResponseEntity<Float> logExercise(@RequestParam int userId, @RequestBody ExerciseRequest exerciseRequest,
                                                   @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            throw new AuthorizationException("Authorization header is missing");
        }

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        if(token.isEmpty()){
            throw new AuthorizationException("Authorization token is missing");
        }

        boolean credentials = jwtDecoderService.validateToken(token);

        if (!credentials){
            throw new AuthorizationException("The Token or Authorization you have given is not Authorized");
        }

        Float exerciseLog = fitnessService.logExercise(userId, exerciseRequest.getActivity(), exerciseRequest.getDuration());

        return new ResponseEntity<>(exerciseLog, HttpStatus.CREATED);
    }

    @GetMapping("/log/{logId}")
    public ResponseEntity<ExerciseLog> getLogById(@PathVariable int logId) {
        ExerciseLog exerciseLog = fitnessService.findLogById(logId);
        return ResponseEntity.ok(exerciseLog);
    }

    @GetMapping("/logs/user/{userId}")
    public ResponseEntity<List<ExerciseLog>> getLogsByUserId(@PathVariable int userId,
                                                             @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            throw new AuthorizationException("Authorization header is missing");
        }

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        if(token.isEmpty()){
            throw new AuthorizationException("Authorization token is missing");
        }

        boolean credentials = jwtDecoderService.validateToken(token);

        if (!credentials){
            throw new AuthorizationException("The Token or Authorization you have given is not Authorized");
        }


        List<ExerciseLog> logs = fitnessService.findLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/score/{userId}")
    public ResponseEntity<Float> getFitnessScore(@PathVariable int userId
    ,@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            throw new AuthorizationException("Authorization header is missing");
        }

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        if(token.isEmpty()){
            throw new AuthorizationException("Authorization token is missing");
        }

        boolean credentials = jwtDecoderService.validateToken(token);

        if (!credentials){
            throw new AuthorizationException("The Token or Authorization you have given is not Authorized");
        }

        float score = fitnessService.calculateFitnessScore(userId);
        return ResponseEntity.ok(score);
    }


}

