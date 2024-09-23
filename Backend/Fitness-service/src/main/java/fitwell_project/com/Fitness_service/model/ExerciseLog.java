package fitwell_project.com.Fitness_service.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercise_logs")
@NoArgsConstructor
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private String activity;

    private int duration;

    private float caloriesBurned;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Automatically set the creation date

    public ExerciseLog(int id, int userId, String activity, int duration, float caloriesBurned, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.activity = activity;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}