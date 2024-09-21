package fitwell_project.com.User.service;

import fitwell_project.com.User.exception.MentalHealthNotFoundException;
import fitwell_project.com.User.model.MentalHealth;
import fitwell_project.com.User.repository.MentalHealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentalHealthService {

    @Autowired
    private MentalHealthRepository mentalHealthRepository;

    public List<MentalHealth> getMentalHealthByUserId(int userId) {
        return mentalHealthRepository.findByUserId(userId);

    }

    public MentalHealth createMentalHealth(MentalHealth mentalHealth){
        return mentalHealthRepository.save(mentalHealth);
    }

    // Retrieve a specific mental health record by its id
    public MentalHealth getMentalHealthById(int id) {
        return mentalHealthRepository.findById(id)
                .orElseThrow(() -> new MentalHealthNotFoundException("Mental Health record with id " + id + " not found"));
    }

    // Delete a mental health record
    public void deleteMentalHealthRecord(int id) {
        MentalHealth mentalHealth = mentalHealthRepository.findById(id)
                .orElseThrow(() -> new MentalHealthNotFoundException("Mental Health record with id " + id + " not found"));
        mentalHealthRepository.delete(mentalHealth);
    }

    public Float calculateAverageScore(int userId) {
        LocalDate today = LocalDate.now();

        // Filter the logs for today
        List<MentalHealth> mentalHealthList = mentalHealthRepository.findByUserId(userId).stream()
                .filter(log -> log.getCreatedAt().toLocalDate().isEqual(today))
                .collect(Collectors.toList());

        // Calculate the average score directly in float
        float averageMentalScore = (float) mentalHealthList.stream()
                .map(MentalHealth::getScore)
                .mapToDouble(Float::doubleValue) // Ensure float to double conversion
                .average()
                .orElse(0.0);

        // Return the average as float
        return averageMentalScore;
    }




}
