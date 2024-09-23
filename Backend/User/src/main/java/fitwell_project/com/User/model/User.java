    package fitwell_project.com.User.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;


    @NoArgsConstructor
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public float getBmi() {
            return bmi;
        }

        public void setBmi(float bmi) {
            this.bmi = bmi;
        }

        public String getActivityLevel() {
            return activityLevel;
        }

        public void setActivityLevel(String activityLevel) {
            this.activityLevel = activityLevel;
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
