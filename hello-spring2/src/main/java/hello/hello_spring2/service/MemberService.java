package hello.hello_spring2.service;

import hello.hello_spring2.domain.Member;
import hello.hello_spring2.repository.MemberRepository;
import hello.hello_spring2.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
@Service
public class MemberService implements MemberServiceImpl{
 */
/*
Transactional
스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다.
만약 런타임 예외가 발생하면 롤백한다.
JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다
 */
@Transactional
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //생성자가 1개만 있으면 @Autowired 생략 가능
    /*
    하단에는 @Autowired생략
     */

    public MemberService(MemberRepository memberRepository, String s) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                /*
                ifPresent
                실행구문에서 null이 발생하면 실행되지 않도록함
                https://sin0824.tistory.com/25 참조
                 */
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
