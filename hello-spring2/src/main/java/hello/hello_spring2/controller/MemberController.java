package hello.hello_spring2.controller;

import hello.hello_spring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //DI (Dependency Injection), 의존성 주입
}
