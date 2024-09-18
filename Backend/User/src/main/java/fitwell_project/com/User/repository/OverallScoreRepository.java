package fitwell_project.com.User.repository;

import fitwell_project.com.User.model.OverallScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverallScoreRepository extends JpaRepository<OverallScore, Integer> {
}
