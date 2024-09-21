package fitwell_project.com.User.service;

import fitwell_project.com.User.exception.OverallScoreNotFoundException;
import fitwell_project.com.User.exception.UserNotFoundException;
import fitwell_project.com.User.feign.DietClient;
import fitwell_project.com.User.feign.FitnessClient;
import fitwell_project.com.User.model.OverallScore;
import fitwell_project.com.User.model.User;
import fitwell_project.com.User.repository.OverallScoreRepository;
import fitwell_project.com.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallScoreService {

    @Autowired
    private OverallScoreRepository overallScoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FitnessClient fitnessClient;


    @Autowired
    private DietClient dietClient;

    @Autowired
    private MentalHealthService mentalHealthService;

    public List<OverallScore> getAllOverallScores() {
        return overallScoreRepository.findAll();
    }

    public OverallScore getOverallScoreById(int id) {
        return overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));
    }

    public OverallScore createOverallScore(OverallScore overallScore) {

        User user = overallScore.getUser();
        if (user == null || user.getId() == 0) {
            throw new UserNotFoundException("User not associated with this overall score or user ID not found.");
        }

        float mentalScore = mentalHealthService.calculateAverageScore(user.getId());
        overallScore.setMentalHealthScore(mentalScore);

        float dietScore = dietClient.getDietScore(user.getId());
        overallScore.setDietScore(dietScore);

        float fitnessScore = fitnessClient.getFitnessScore(user.getId());
        overallScore.setPhysicalScore(fitnessScore);

        float bmiScore = calculateBMIScore(user.getBmi());
        overallScore.setBmiScore(bmiScore);

        float total = overallScore.getPhysicalScore()+ overallScore.getDietScore()+ overallScore.getMentalHealthScore()+ overallScore.getBmiScore();
        overallScore.setTotalScore(total);
        return overallScoreRepository.save(overallScore);
    }


    public OverallScore updateOverallScore(int id, OverallScore overallScoreDetails) {
        OverallScore overallScore = overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));

        overallScore.setPhysicalScore(overallScoreDetails.getPhysicalScore());
        overallScore.setDietScore(overallScoreDetails.getDietScore());
        overallScore.setMentalHealthScore(overallScoreDetails.getMentalHealthScore());
        overallScore.setBmiScore(overallScore.getBmiScore());
        float total = overallScore.getPhysicalScore()+ overallScore.getDietScore()+ overallScore.getMentalHealthScore()+ overallScore.getBmiScore();
        overallScore.setTotalScore(total);
        return overallScoreRepository.save(overallScore);
    }

    public void deleteOverallScore(int id) {
        OverallScore overallScore = overallScoreRepository.findById(id)
                .orElseThrow(() -> new OverallScoreNotFoundException("Overall Score with id " + id + " not found"));

        overallScoreRepository.delete(overallScore);
    }


    public float calculateBMIScore(float bmi) {
        if (bmi <= 0) {
            throw new IllegalArgumentException("BMI must be a positive number.");
        }
        float score = 25 - (Math.abs(bmi - 22) * 2);
        if (score < 1) {
            return (float) 1;
        }
        if (score > 25) {
            return (float) 25;
        }
        return score;
    }

}
