package fitwell_project.com.User.controller;

import fitwell_project.com.User.exception.MentalHealthNotFoundException;
import fitwell_project.com.User.model.MentalHealth;
import fitwell_project.com.User.service.MentalHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mental-health")
public class MentalHealthController {

    private final MentalHealthService mentalHealthService;

    @Autowired
    public MentalHealthController(MentalHealthService mentalHealthService) {
        this.mentalHealthService = mentalHealthService;
    }

    // Get all mental health records for a specific user by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MentalHealth>> getMentalHealthByUserId(@PathVariable int userId) {
        List<MentalHealth> mentalHealthList = mentalHealthService.getMentalHealthByUserId(userId);
        if (mentalHealthList.isEmpty()) {
            throw new MentalHealthNotFoundException("No mental health records found for user with id " + userId);
        }
        return new ResponseEntity<>(mentalHealthList, HttpStatus.OK);
    }

    // Get a specific mental health record by id
    @GetMapping("/{id}")
    public ResponseEntity<MentalHealth> getMentalHealthById(@PathVariable int id) {
        MentalHealth mentalHealth = mentalHealthService.getMentalHealthById(id);
        return new ResponseEntity<>(mentalHealth, HttpStatus.OK);
    }

    // Create a new mental health record
    @PostMapping
    public ResponseEntity<MentalHealth> createMentalHealth(@RequestBody MentalHealth mentalHealth) {
        MentalHealth createdMentalHealth = mentalHealthService.createMentalHealth(mentalHealth);
        return new ResponseEntity<>(createdMentalHealth, HttpStatus.CREATED);
    }

    // Delete a mental health record by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentalHealthRecord(@PathVariable int id) {
        mentalHealthService.deleteMentalHealthRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
