package fitwell_project.com.DietService.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private int age;
    private String gender;
    private float height;
    private float weight;
    private float bmi;
    private String activityLevel;


    }


