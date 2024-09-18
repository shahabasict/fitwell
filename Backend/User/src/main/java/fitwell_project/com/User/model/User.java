    package fitwell_project.com.User.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String sex;
        private int age;
        private String username;
        private String password;
        private String email;
        private float height;
        private float weight;
        private float bmi;
        private String activityLevel;

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

    }
