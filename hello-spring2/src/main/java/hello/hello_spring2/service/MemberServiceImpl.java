package hello.hello_spring2.service;

import hello.hello_spring2.domain.Member;
import hello.hello_spring2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberServiceImpl {


    public Long join(Member member);

    public void validateDuplicateMember(Member member);

    public List<Member> findMembers();

    public Optional<Member> findOne(Long memberId);
}
