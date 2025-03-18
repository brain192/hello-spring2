package hello.hello_spring2.repository;

import hello.hello_spring2.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원 가입
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)",
                user.getUsername(), user.getPassword());
    }

    // 사용자 조회 (로그인 시 사용)
    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?",
                (rs, rowNum) -> new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")),
                username);
    }
}
