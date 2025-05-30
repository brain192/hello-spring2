package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Board3;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB 접근 로직 처리 클래스
 * JdbcTemplate을 이용해 SQL 수행
 */
@Repository
public class BoardRepository3 {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository3(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 게시글 저장
     */
    public void save(Board3 board) {
        String sql = "INSERT INTO board (title, content, writer, filename, original_filename) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                board.getTitle(), board.getContent(), board.getWriter(),
                board.getFilename(), board.getOriginalFilename());
    }

    /**
     * 게시글 ID로 조회
     */
    public Board3 findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Board3 b = new Board3();
            b.setId(rs.getLong("id"));
            b.setTitle(rs.getString("title"));
            b.setContent(rs.getString("content"));
            b.setWriter(rs.getString("writer"));
            b.setFilename(rs.getString("filename"));
            b.setOriginalFilename(rs.getString("original_filename"));
            return b;
        }, id);
    }

    /**
     * 전체 게시글 목록 조회
     */
    public List<Board3> findAll() {
        return jdbcTemplate.query("SELECT * FROM board ORDER BY id DESC", (rs, rowNum) -> {
            Board3 b = new Board3();
            b.setId(rs.getLong("id"));
            b.setTitle(rs.getString("title"));
            b.setContent(rs.getString("content"));
            b.setWriter(rs.getString("writer"));
            b.setFilename(rs.getString("filename"));
            b.setOriginalFilename(rs.getString("original_filename"));
            return b;
        });
    }
}
