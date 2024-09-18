package fitwell_project.com.User.service;

import fitwell_project.com.User.exception.OverallScoreNotFoundException;
import fitwell_project.com.User.model.OverallScore;
import fitwell_project.com.User.repository.OverallScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallScoreService {

    @Autowired
    private OverallScoreRepository overallScoreRepository;


    public List<OverallScore> getAllOverallScores() {
        return overallScoreRepository.findAll();
    }

    public OverallScore getOverallScoreById(int id) {
        return overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));
    }

    public OverallScore createOverallScore(OverallScore overallScore) {
        return overallScoreRepository.save(overallScore);
    }

    public OverallScore updateOverallScore(int id, OverallScore overallScoreDetails) {
        OverallScore overallScore = overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));

        overallScore.setPhysicalScore(overallScoreDetails.getPhysicalScore());
        overallScore.setDietScore(overallScoreDetails.getDietScore());
        overallScore.setMentalHealthScore(overallScoreDetails.getMentalHealthScore());
        overallScore.setTotalScore(overallScoreDetails.getTotalScore());

        return overallScoreRepository.save(overallScore);
    }

    public void deleteOverallScore(int id) {
        OverallScore overallScore = overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));

        overallScoreRepository.delete(overallScore);
    }
}
