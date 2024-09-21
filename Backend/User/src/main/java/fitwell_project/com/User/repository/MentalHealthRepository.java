package fitwell_project.com.User.repository;

import fitwell_project.com.User.model.MentalHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentalHealthRepository extends JpaRepository<MentalHealth,Integer> {

    List<MentalHealth> findByUserId(int userId);
}
