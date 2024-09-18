package fitwell_project.com.Fitness_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercise_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}