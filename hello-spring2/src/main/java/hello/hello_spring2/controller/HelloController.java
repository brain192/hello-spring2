package hello.hello_spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
PostMapping / GetMapping

@PostMapping : HTTP Post Method에 해당하는 단축 표현으로 서버에 리소스를 등록(저장)할 때 사용
@GetMapping : HTTP Get Method에 해당하는 단축 표현으로 서버의 리소스를 조회할 때 사용
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        /*
        Model addAttribute(Object value)
        1) value를 추가한다. value의 패키지 이름을 제외한 단순 클래스 이름을 모델 이름으로 사용한다. 이 때 첫 글자는 소문자로 처리한다.
        2) value가 배열이거나 컬렉션인 경우 첫 번째 원소의 클래스 이름 뒤에 "List"를 붙인 걸 모델 이름으로 사용한다. 이 경우에도 클래스 이름의 첫자는 소문자로 처리한다.
         */
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name",required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
        //RequestParam에서 value 값이 있으면 해당하는 required = false
    }
    /*
    @ResponseBody
    VO객체를 JSON으로 바꿔 HTTP Body 안에 담는 어노테이션
    viewResolver를 사용하지 않음

    @RequestParam
    파라미터 이름으로 바인딩한다
    /request-param?username=yum&age=20
    위처럼 요청이 들어오면, username=yum, age=20이 바인딩 된다
     */
    @GetMapping("/hello-string")
    @ResponseBody       //json return values
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
