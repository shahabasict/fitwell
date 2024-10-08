package fitwell_project.com.DietService.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "diets")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "diet")
    private String diet;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Diet(Integer id, Integer userId, String diet, Integer calories, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.diet = diet;
        this.calories = calories;
        this.createdAt = createdAt;
    }

    public Diet() {

    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getDiet() { return diet; }
    public void setDiet(String diet) { this.diet = diet; }
    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
