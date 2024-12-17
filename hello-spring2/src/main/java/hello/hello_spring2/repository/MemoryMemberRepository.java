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

    NullPointerException을 방지해주는. 즉, null인 값을 참조해도 NullPointerException이 발생하지 않도록 값을 래퍼로 감싸주는 타입
    NullPointerException:JAVA에서 해당 문제가 많이 발생함
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
        /*
        ArrayList 장단점
        리스트의 길이가 가변적이다. 이를 동적 할당(dynamic allocation)이라고 한다.
        배열과 달리 메모리에 연속적으로 나열되어있지 않고 주소로 연결되어있는 형태이기 때문에 index를 통한 색인(접근)속도가 배열보다는 느리다.
        데이터(element) 사이에 빈 공간을 허용하지 않는다.
        객체로 데이터를 다루기 때문에 적은양의 데이터만 쓸 경우 배열에 비해 차지하는 메모리가 커진다.
         */
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
        stream의 filter함수
        filter() 메서드는 주어진 조건(Predicate)을 만족하는 요소들로 구성된 스트림을 반환

        findAny()는 Stream에서 가장 먼저 탐색되는 요소를 리턴
        
        ==과 equals() 연산의 차이
        ==는 주소값이 같은지 아닌지 비교하는 것이고, equals()연산도 내부적으로 주소값을 비교하지만 String클래스에서는 equals()를 재정의해 내용을 비교하게 되어있다.
         */
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    /*
    clear()
    배열이나 리스트의 모든 값을 제거
     */
    public void clearStore() {
        store.clear();
    }
}
