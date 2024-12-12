package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository (퍼시스턴스 레이어, DB나 파일같은 외부 I/O 작업을 처리함)
/*
extend
부모의 메소드를 그대로 사용할 수 있으며 오버라이딩 할 필요 없이 부모에 구현되있는 것을 직접 사용 가능하다.

implements
부모의 메소드를 반드시 오버라이딩(재정의)해야 한다.
 */
@Repository
public class MemoryMemberRepository implements MemberRepository{
    /*
    Map이란?
    Map은 리스트나 배열처럼 순차적으로(sequential) 해당 요소 값을 구하지 않고 key를 통해 value를 얻는다.
    맵(Map)의 가장 큰 특징이라면 key로 value를 얻어낸다는 점이다.
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //++가 앞인 경우 참조되기 전에 증가한다.
        member.setId(++sequence);
        store.put(member.getId(), member);

        System.out.println();

        return member;
    }

    /*
    Optional는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
    https://velog.io/@kjgi73k/JAVA-Optional%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
    참조해서 공부
    */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /*
    List옵션에 대해서는
    https://velog.io/@hameee/Java-List
    참조해서 공부
    
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        store.clear();
    }
}
