package fitwell_project.com.User.model;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "mental_health_scores")
public class MentalHealth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Integer userId;

    private float score;

    private enum severity{
        LOW,
        MEDIUM,
        HIGH
    };

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public MentalHealth(int id, Integer userId, float score, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
