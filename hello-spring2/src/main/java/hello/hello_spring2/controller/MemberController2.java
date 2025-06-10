package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Member2;
import hello.hello_spring2.service.MemberService2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController2 {

    private final MemberService2 service;

    public MemberController2(MemberService2 service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // register.html 렌더링
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Member2 member) {
        service.register(member); // 회원가입 처리
        return "redirect:/login"; // 로그인 페이지로 이동
    }
}
