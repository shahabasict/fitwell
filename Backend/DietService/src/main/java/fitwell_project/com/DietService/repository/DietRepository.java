package fitwell_project.com.DietService.repository;

import fitwell_project.com.DietService.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {
    List<Diet> findByUserId(Integer userId);
}
