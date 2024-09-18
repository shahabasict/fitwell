package fitwell_project.com.Fitness_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FitnessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessServiceApplication.class, args);
	}

}
