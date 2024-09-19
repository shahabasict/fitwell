package fitwell_project.com.User.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseLogDto {
    private int id;
    private int userId;
    private String activity;
    private int duration;
    private float caloriesBurned;
}