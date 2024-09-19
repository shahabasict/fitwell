package fitwell_project.com.User.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "diet-service", url = "http://localhost:8082/api/diet")  // Update with the correct URL
public interface DietClient {

    @GetMapping("/score/{userId}")
    float getDietScore(@PathVariable int userId);
}