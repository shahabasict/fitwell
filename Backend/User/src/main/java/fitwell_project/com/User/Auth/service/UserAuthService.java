package fitwell_project.com.User.Auth.service;

import fitwell_project.com.User.model.User;
import fitwell_project.com.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserRepository repo;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private JwtService jwtService;


    public String register(User user) {

        if (user.getId() != 0 && repo.existsById(user.getId())) {
            throw new RuntimeException("A user with this ID already exists.");
        }

        if (repo.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("A user with this username already exists.");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        float bmi = user.getWeight()/((user.getHeight()/100)* (user.getHeight()/100));
        user.setBmi(bmi);
        repo.save(user);
        return "User Registered Successfully";
    }

    public String login(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User user1 = userRepository.findByUsername(user.getUsername());
        String userId = String.valueOf(user1.getId());

        String ID = "UID:"+userId;
        if (authentication.isAuthenticated()) {
            return (jwtService.generateToken(user.getUsername())+ID);
        } else {
            return null;
        }
    }
//    public record tokenreturn(String userID, String token){}


}
