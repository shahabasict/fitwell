package fitwell_project.com.User.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "overall_scores")
public class OverallScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id") // foreign key to User
    private User user;

    private float physicalScore;

    private float dietScore;

    private float mentalHealthScore;

    private float bmiScore;

    private float totalScore;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public OverallScore(int id, User user, float physicalScore, float dietScore, float mentalHealthScore, float bmiScore, float totalScore, Date createdAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.physicalScore = physicalScore;
        this.dietScore = dietScore;
        this.mentalHealthScore = mentalHealthScore;
        this.bmiScore = bmiScore;
        this.totalScore = totalScore;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPhysicalScore() {
        return physicalScore;
    }

    public void setPhysicalScore(float physicalScore) {
        this.physicalScore = physicalScore;
    }

    public float getDietScore() {
        return dietScore;
    }

    public void setDietScore(float dietScore) {
        this.dietScore = dietScore;
    }

    public float getMentalHealthScore() {
        return mentalHealthScore;
    }

    public void setMentalHealthScore(float mentalHealthScore) {
        this.mentalHealthScore = mentalHealthScore;
    }

    public float getBmiScore() {
        return bmiScore;
    }

    public void setBmiScore(float bmiScore) {
        this.bmiScore = bmiScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
