package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Member2;
import hello.hello_spring2.repository.MemberRepository2;
import hello.hello_spring2.service.MemberService2;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController2 {

    private final MemberService2 service;
    /*
    public MemberController2(MemberService2 service) {
        this.service = service;
    }

     */

    private final MemberRepository2 memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // register.html 렌더링
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Member2 member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));  // 비밀번호 암호화
        memberRepository.save(member);
        return "redirect:/logins"; // 로그인 페이지로 이동
    }

    @GetMapping("/logins")
    public String loginForm() {
        return "logins";
    }

    @PostMapping("/logins")
    public String login(@RequestParam("username")  String username,
                        @RequestParam("password")  String password,
                        HttpSession session,
                        Model model) {
        Member2 loginMember = service.login(username, password);
        if (loginMember != null) {
            session.setAttribute("loginMember", loginMember); // 로그인 세션 설정
            return "redirect:/welcome"; // 로그인 성공 시 페이지 이동
        } else {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
            return "logins"; // 다시 로그인 화면
        }
    }

    @GetMapping("/logouts")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
        return "redirect:/logins";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome"; // welcome.html 페이지
    }
}
