package hello.hello_spring2.controller;

import org.hibernate.sql.ast.tree.expression.Summarization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.hello_spring2.domain.Cals1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.sum;

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
    ex)
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

    @GetMapping("/cal1")
    public String cal1() {
        return "cal1";
    }

    @GetMapping("/cal1_ok")
    public String cal1_ok(@RequestParam("cal1") double cal1, @RequestParam("cal2") double cal2,
                          @RequestParam("see") String see, Model model) {


        double cal3;

        String a="test";


        if(Objects.equals(see, "a")) {
            cal3=cal1+cal2;
            a="no";
        }else if(Objects.equals(see, "b")){
            cal3=cal1-cal2;
        }else if(Objects.equals(see, "c")){
            cal3=cal1*cal2;
        }else if(Objects.equals(see, "d")){
            cal3=cal1/cal2;
        }else{
            cal3=0;
        }
        System.out.println(cal1);
        System.out.println(cal2);
        System.out.println(see);
        System.out.println(cal3);
        System.out.println(a);

        model.addAttribute("cal3",cal3);

        return "cal1_ok";
    }

    @GetMapping("/cal2")
    public String cal2() {
        return "cal2";
    }

    @GetMapping("/cal2_ok")
    public String cal2_ok(@RequestParam("see") String see, Model model) {

        model.addAttribute("see",see);

        System.out.println(see);

        Stack<String> s = new Stack<>();
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+|\\D");
        Matcher m = p.matcher(see);

        while (m.find()) {
            String split = m.group();
            list.add(split);
        }

        for (int i = 0; i < list.size(); i++) {
            if (!s.isEmpty() && s.peek().equals("*")) {
                // * 연산자 제거
                s.pop();

                // 계산할 수 가져오기
                String pop = s.pop();

                // 곱셈 계산 후 다시 스택에 입력
                s.push(String.valueOf(Integer.parseInt(pop) * Integer.parseInt(list.get(i))));
            } else {
                s.push(list.get(i));
            }
        }

        int result = Integer.parseInt(s.get(0));

        for (int i = 1; i < s.size(); i += 2) {
            String op = s.get(i);
            switch (op) {
                case "+":
                    result += Integer.parseInt(s.get(i + 1));
                    break;
                case "-":
                    result -= Integer.parseInt(s.get(i + 1));
            }
        }

        System.out.println(result);

        model.addAttribute("result",result);

        return "cal2_ok";
    }
}
