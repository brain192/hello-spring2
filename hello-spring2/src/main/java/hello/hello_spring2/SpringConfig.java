package hello.hello_spring2;

/*
import hello.hello_spring2.repository.JdbcMemberRepository;
import hello.hello_spring2.repository.JdbcTemplateMemberRepository;
import hello.hello_spring2.repository.MemberRepository;
import hello.hello_spring2.repository.MemoryMemberRepository;
import hello.hello_spring2.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */
    /*
    DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 스프링 부트는 데이터베이스 커넥션 정보를
    바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다
    */

/*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
    //bean 생성
}
 */

import hello.hello_spring2.repository.*;
import hello.hello_spring2.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@Configuration이라고 하면 설정파일을 만들기 위한 어노테이션 or Bean을 등록하기 위한 어노테이션이다.

Bean과 Component 차이

@Bean은 메소드 레벨에서 선언하며, 반환되는 객체(인스턴스)를 개발자가 수동으로 빈으로 등록하는 어노테이션이다.

@Component는 클래스 레벨에서 선언함으로써 스프링이 런타임시에 컴포넌트스캔을 하여 자동으로 빈을 찾고(detect) 등록하는 어노테이션이다.
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}