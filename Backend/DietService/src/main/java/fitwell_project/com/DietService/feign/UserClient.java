package fitwell_project.com.DietService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-service", url = "http://localhost:8080/api")
public interface UserClient {

    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable int userId);


}
