package fitwell_project.com.User.controller;


import fitwell_project.com.User.model.OverallScore;
import fitwell_project.com.User.service.OverallScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overallscores")
public class OverallScoreController {

    @Autowired
    private OverallScoreService overallScoreService;

    @GetMapping
    public List<OverallScore> getAllOverallScores() {
        return overallScoreService.getAllOverallScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OverallScore> getOverallScoreById(@PathVariable int id) {
        OverallScore overallScore = overallScoreService.getOverallScoreById(id);
        return ResponseEntity.ok(overallScore);
    }

    @PostMapping
    public ResponseEntity<Float> createOverallScore(@RequestBody OverallScore overallScore) {
        Float createdOverallScore = overallScoreService.createOverallScore(overallScore);
        return ResponseEntity.ok(createdOverallScore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OverallScore> updateOverallScore(@PathVariable int id, @RequestBody OverallScore overallScoreDetails) {
        OverallScore updatedOverallScore = overallScoreService.updateOverallScore(id, overallScoreDetails);
        return ResponseEntity.ok(updatedOverallScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOverallScore(@PathVariable int id) {
        overallScoreService.deleteOverallScore(id);
        return ResponseEntity.noContent().build();
    }
}
