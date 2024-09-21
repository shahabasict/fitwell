package fitwell_project.com.User.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
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
}
