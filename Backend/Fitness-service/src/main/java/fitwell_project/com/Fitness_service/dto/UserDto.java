package fitwell_project.com.Fitness_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String sex;
    private int age;
    private float height;
    private float weight;
    private String activityLevel;
}
