package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member;

public interface MemberRepository {
    Member save(Member member);
}
