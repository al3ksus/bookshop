package shop.onlinebookshop.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.aspectj.annotation.LazySingletonAspectInstanceFactoryDecorator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.onlinebookshop.models.users.User;
import shop.onlinebookshop.service.UserService;
import shop.onlinebookshop.utils.Constant;
import shop.onlinebookshop.utils.Validator;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final Validator validator;
    private final UserService userService;

    @GetMapping
    public String main() {
        return "main";
    }

    @PostMapping
    public String logIn(@RequestParam String login, String password, Map<String, Object> model) {
        if (!userService.userExists(login, password)) {
            model.put("error", Constant.ERROR_LOGIN_OR_PASSWORD_MESSAGE);
            return "main";
        }

        User user = userService.getUserByLogin(login);
        model.put("user", user);

        return userService.getPageByRole(user.getRole());
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam(value = "role", required = false) String role,
            Map<String, Object> model
    ) {
        if (!validator.isLoginCorrect(login)) {
            model.put("error", Constant.UNACCEPTABLE_LOGIN_MESSAGE);
            return "register";
        }

        if(userService.isLoginTaken(login)) {
            model.put("error", Constant.ERROR_LOGIN_TAKEN_MESSAGE);
            return "register";
        }

        if (!validator.isPasswordCorrect(password)) {
            model.put("error", Constant.UNACCEPTABLE_PASSWORD_MESSAGE);
            return "register";
        }

        if (userService.isUserNameEmpty(firstName, lastName)) {
            model.put("error", Constant.EMPTY_NAME_FIELD_MESSAGE);
            return "register";
        }

        model.put("user", userService.addUser(login, password, firstName, lastName, role));
        model.put("notification", Constant.SUCCESS_REGISTRATION_MESSAGE);

        return "register";
    }


    @GetMapping("/author/{login}")
    public String author(@PathVariable String login, Map<String, Object> model) {
        User user = userService.getUserByLogin(login);
        model.put("user", user);

        return userService.getPageByRole(user.getRole());
    }

    @GetMapping("/customer/{login}")
    public String customer(@PathVariable String login, Map<String, Object> model) {
        User user = userService.getUserByLogin(login);
        model.put("user", user);

        return userService.getPageByRole(user.getRole());
    }

}
