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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Float createOverallScore(OverallScore overallScore) {

        Optional<User> user = userRepository.findById(overallScore.getUser().getId());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not associated with this overall score or user ID not found.");
        }else {

            System.out.println("User Retrieved");
            float mentalScore = mentalHealthService.calculateAverageScore(user.get().getId());
            overallScore.setMentalHealthScore(mentalScore);
            System.out.println("Mental Score set");

            float dietScore = dietClient.getDietScore(user.get().getId());
            overallScore.setDietScore(dietScore);
            System.out.println("diet Score set");

            float fitnessScore = fitnessClient.getFitnessScore(user.get().getId());
            overallScore.setPhysicalScore(fitnessScore);
            System.out.println("fitness Score set");

            float bmiScore = calculateBMIScore(user.get().getBmi());
            overallScore.setBmiScore(bmiScore);
            System.out.println("bmi Score set");

            float total = overallScore.getPhysicalScore() + overallScore.getDietScore() + overallScore.getMentalHealthScore() + overallScore.getBmiScore();
            overallScore.setTotalScore(total);

            System.out.println("total Score set");

            return total;
        }

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
