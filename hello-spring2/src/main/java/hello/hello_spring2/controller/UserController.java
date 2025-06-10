package hello.hello_spring2.controller;

import hello.hello_spring2.domain.User;
import hello.hello_spring2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authsss")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registersss")
    public String registerForm() {
        return "/auth/register";
    }

    @PostMapping("/registersss")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/auth/registersss?error=true";
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(null, username, hashedPassword, name, null);
        userRepository.save(user);

        return "redirect:/auth/loginsss";
    }
}