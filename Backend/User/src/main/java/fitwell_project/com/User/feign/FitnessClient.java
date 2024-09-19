package fitwell_project.com.User.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fitness-service", url = "http://localhost:8081/fitness")  // Update with the correct URL
public interface FitnessClient {

    @GetMapping("/score/{userId}")
    float getFitnessScore(@PathVariable int userId);
}
