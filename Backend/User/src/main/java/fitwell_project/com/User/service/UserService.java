package fitwell_project.com.User.service;

import fitwell_project.com.User.exception.UserNotFoundException;
import fitwell_project.com.User.model.User;
import fitwell_project.com.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public User createUser(User user) {
        float bmi = user.getWeight()/((user.getHeight()/100)* (user.getHeight()/100));
        user.setBmi(bmi);
        return userRepository.save(user);
    }

    public User updateUser(int id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setHeight(userDetails.getHeight());
        user.setWeight(userDetails.getWeight());
        user.setBmi(userDetails.getBmi());

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);
    }
}
