package fitwell_project.com.Fitness_service.feign;

import fitwell_project.com.Fitness_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/api")
public interface UserClient {

    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable int userId);
}