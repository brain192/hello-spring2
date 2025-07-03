package hello.hello_spring2.service;

import hello.hello_spring2.domain.Member2;
import hello.hello_spring2.repository.MemberRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberDetailsService2 implements UserDetailsService {

    private final MemberRepository2 memberRepository;

    /**
     * username으로 회원 조회 후 UserDetails 객체 반환.
     * → Spring Security 내부에서 로그인 인증 처리에 사용됨.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Member2 member = memberRepository.findByUsername(username);
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .authorities(Collections.emptyList())  // 권한 사용 안 함
                .build();
    }

}
