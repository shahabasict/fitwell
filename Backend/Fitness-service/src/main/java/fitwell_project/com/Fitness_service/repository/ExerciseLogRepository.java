package fitwell_project.com.Fitness_service.repository;

import fitwell_project.com.Fitness_service.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Integer> {
    List<ExerciseLog> findByUserId(int userId);
}
