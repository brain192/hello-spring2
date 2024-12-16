package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Member;
import hello.hello_spring2.domain.MemberForm;
import hello.hello_spring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 프로그램 명
 * -
 * 생성일 :
 * 작성자 :
 *
 * ------------------------------------------
 * 변경이력
 *
 *
 */
/*
ctrl로 어디에서 쓰이는지 확인 가능

ctrl+alt+<-키로 반대로 이동 가능
 */
@Controller
public class MemberController {

    //------------------------------------------------------------------
    //멤버변수
    private final MemberService memberService;

    //------------------------------------------------------------------
    //생성자
    //DI (Dependency Injection), 의존성 주입
    /*
        ㅇㄹㅇㄻㅇㄹ
        ㅁㅇㄻㅇㄹ

        ㅁㅇㄹ
        ㅁㅇㄹ
        ㅁㄹㅇ

     */

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    HttpServletRequest
    http프로토콜의 request 정보를 서블릿에게 전달하기 위한 목적으로 사용한다.
    헤더정보, 파라미터, 쿠키, URL 등의 정보를 읽어 들이는 메소드를 가지고 있다.
    Body의 Stream을 읽어 들이는 메소드를 가지고 있다.

    HttpServletResponse
    WAS는 어떤 클라이언트가 요청을 보냈는지 알고 있고, 해당 클라이언트에게 응답을 보내기 위한
    HttpServletResponse객체를 생성하여 서블릿에 전달한다.
    서블릿은 해당 객체를 이용하여 content type, 응답코드, 응답 메시지 등을 전송한다.
    클라이언트가 서버에게 요청을 보낼 때 굉장히 많은 정보들을 함께 가지고 요청을 하게 된다.
     */



    //------------------------------------------------------------------
    //url mapping
//    @RequestMapping("/members/new")
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    /**
     * <p> ~~~ 생성 </p>
     * 무엇을 생성한다.
     *
     *
     * @param form
     * @return
     */
    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }
    //@PostMapping : HTTP Post Method에 해당하는 단축 표현으로 서버에 리소스를 등록(저장)할 때 사용 //Hello컨트롤러 주석 참조

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    //@GetMapping : HTTP Get Method에 해당하는 단축 표현으로 서버의 리소스를 조회할 때 사용 //Hello컨트롤러 주석 참조
}
