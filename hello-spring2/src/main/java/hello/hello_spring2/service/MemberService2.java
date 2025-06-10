package hello.hello_spring2.service;

import hello.hello_spring2.domain.Member2;
import hello.hello_spring2.repository.MemberRepository2;
import org.springframework.stereotype.Service;

/**
 * 회원가입 관련 비즈니스 로직 처리
 */
@Service
public class MemberService2 {

    private final MemberRepository2 repository;
    //생성자 주입 불변성 유지: 생성자에서 한 번만 주입되므로 중간에 변경 불가
    public MemberService2(MemberRepository2 repository) {
        this.repository = repository;
    }

    public void register(Member2 member) {
        // 향후 비밀번호 암호화, 중복 확인 등을 추가할 수 있음
        repository.save(member);
    }
}