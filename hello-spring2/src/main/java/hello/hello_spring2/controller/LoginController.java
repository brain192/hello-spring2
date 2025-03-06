package hello.hello_spring2.controller;

import hello.hello_spring2.domain.User;
import hello.hello_spring2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 폼
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // 회원 가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.register(user);
        return "redirect:/auth/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (userService.login(username, password)) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/boards";
        } else {
            model.addAttribute("error", "로그인 실패: 아이디 또는 비밀번호를 확인하세요.");
            return "login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
