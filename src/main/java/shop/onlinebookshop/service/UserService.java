package shop.onlinebookshop.service;

import java.util.*;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.onlinebookshop.models.users.*;
import shop.onlinebookshop.repository.UserRepository;
import shop.onlinebookshop.utils.Constant;
import shop.onlinebookshop.utils.Validator;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public boolean userExists(String login, String password) {
        Optional<User> user = userRepository.findByLogin(login);

        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public boolean isLoginTaken(String login) {
        return userRepository.existsByLogin(login);
    }

    public User getUserByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public String getPageByRole(Role role) {
        if (role.equals(Role.AUTHOR)) {
            return "author-main-page";
        }

        return "customer-main-page";
    }

    public List<User> getAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public boolean isUserNameEmpty(String firstName, String lastName) {
        return firstName.isBlank() || lastName.isBlank();
    }

    public User addUser(String login, String password, String firstName, String lastName, String role) {
        User user = new User(login, password, firstName, lastName, Role.AUTHOR);

        if (role == null) {
            user.setRole(Role.CUSTOMER);
        }

        userRepository.save(user);

        return user;
    }

}

