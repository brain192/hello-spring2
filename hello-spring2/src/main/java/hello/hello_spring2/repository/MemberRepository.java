package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member;

import java.util.List;
import java.util.Optional;

/*
인터페이스란? 극단적으로 동일한 목적 하에 동일한 기능을 보장하게 하기 위함
어떻게? 자바의 다형성을 이용하여  개발코드 수정을 줄이고 유지보수성을 높인다.

인터페이스는 interface 키워드를 통해 선언할 수 있으며 implements 키워드를 통해 일반 클래스에서 인터페이스를 구현할 수 있다.
 */
public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    Optional<Member> findByName(String name);
}
