package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Member2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 회원 정보 DB 처리 클래스
 */
@Repository
public class MemberRepository2 {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Member2 member) {
        String sql = "INSERT INTO member (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getUsername(), member.getPassword());
    }
}
