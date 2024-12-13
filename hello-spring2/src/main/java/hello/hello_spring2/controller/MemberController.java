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
@Controller
public class MemberController {

    //------------------------------------------------------------------
    //맴버변수
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
