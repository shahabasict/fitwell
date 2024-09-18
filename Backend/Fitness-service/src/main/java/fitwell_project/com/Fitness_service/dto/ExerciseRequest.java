package fitwell_project.com.Fitness_service.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ExerciseRequest {
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ExerciseRequest(String activity, int duration) {
        this.activity = activity;
        this.duration = duration;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @NotBlank(message = "Activity is required")
    private String activity;
    @Min(value = 1, message = "Duration should be at least 1 minute")
    private int duration;

    // Getters and setters
}