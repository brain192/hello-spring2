package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/*
12/18 개인과제
jpa방식과 jabc방식의 차이 이해

스프링 데이터 JDBC는 성능 최적화와 직접적인 SQL 제어에 유리한 반면, JPA는 객체 지향적인 프로그래밍과 복잡한 매핑 작업에 유리
 */
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}