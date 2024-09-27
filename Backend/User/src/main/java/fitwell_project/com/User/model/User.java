    package fitwell_project.com.User.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;


    @Setter
    @Getter
    @NoArgsConstructor
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private String gender;
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

        public User(int id, String name, String gender, int age, String username, String password, String email, float height, float weight, float bmi, String activityLevel, Date createdAt, Date updatedAt) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.username = username;
            this.password = password;
            this.email = email;
            this.height = height;
            this.weight = weight;
            this.bmi = bmi;
            this.activityLevel = activityLevel;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
