package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Board2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository2 {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 저장
    public void save(@org.jetbrains.annotations.NotNull Board2 board) {
        jdbcTemplate.update("INSERT INTO board (title, content, writer, filename, original_filename) VALUES (?, ?, ?, ?, ?)",
                board.getTitle(), board.getContent(), board.getWriter(), board.getFilename(), board.getOriginalFilename());
    }

    // 전체 게시글 조회
    public List<Board2> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", boardRowMapper());
    }

    // 게시글 상세 조회
    public Optional<Board2> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM board WHERE id = ?", boardRowMapper(), id)
                .stream().findFirst();
    }

    // 게시글 조회 결과를 Board 객체로 매핑하는 메서드
    private RowMapper<Board2> boardRowMapper() {
        return (rs, rowNum) -> {
            Board2 board = new Board2();
            board.setId(rs.getLong("id"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setWriter(rs.getString("writer"));
            board.setFilename(rs.getString("filename"));
            board.setOriginalFilename(rs.getString("original_filename"));
            return board;
        };
    }
}
