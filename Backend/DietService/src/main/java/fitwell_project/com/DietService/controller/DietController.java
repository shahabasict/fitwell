package fitwell_project.com.DietService.controller;

import fitwell_project.com.DietService.exception.ApiRequestException;
import fitwell_project.com.DietService.exception.AuthorizationException;
import fitwell_project.com.DietService.exception.DietLogNotFoundException;
import fitwell_project.com.DietService.model.Diet;
import fitwell_project.com.DietService.service.DietService;
import fitwell_project.com.DietService.util.JwtDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet")
public class DietController {

    @Autowired
    private JwtDecoderService jwtDecoderService;

    @Autowired
    private DietService dietService;

    @PostMapping("/log")
    public ResponseEntity<Diet> logDiet(@RequestParam int userId, @RequestParam String dietDescription,
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


        try {
            Diet dietLog = dietService.logDiet(userId, dietDescription);
            return new ResponseEntity<>(dietLog, HttpStatus.CREATED);
        } catch (ApiRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/today")
    public ResponseEntity<List<Diet>> getTodayLogs(@RequestParam int userId) {
        try {
            List<Diet> logs = dietService.findLogsByUserIdForToday(userId);
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/{logId}")
    public ResponseEntity<Diet> getLogById(@PathVariable int logId) {
        try {
            Diet dietLog = dietService.findLogById(logId);
            return new ResponseEntity<>(dietLog, HttpStatus.OK);
        } catch (DietLogNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ApiRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Diet>> getLogsByUserId(@RequestParam int userId) {
        try {
            List<Diet> logs = dietService.findLogsByUserId(userId);
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allLog")
    public ResponseEntity<List<Diet>> findAllLog(){
        return ResponseEntity.ok(dietService.findAllLogs());
    }


    @GetMapping("/score/{userId}")
    public ResponseEntity<Float> getDietScore(@PathVariable int userId) {
        float score = dietService.calculateDietScore(userId);
        return ResponseEntity.ok(score);
    }

}
