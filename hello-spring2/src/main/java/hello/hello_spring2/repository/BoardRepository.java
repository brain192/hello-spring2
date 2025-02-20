package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) ->
            new Board(rs.getLong("id"), rs.getString("title"), rs.getString("content"));

    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", boardRowMapper);
    }

    public Board findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM board WHERE id = ?", boardRowMapper, id);
    }

    public int save(Board board) {
        return jdbcTemplate.update("INSERT INTO board (title, content) VALUES (?, ?)",
                board.getTitle(), board.getContent());
    }

    public int update(Board board) {
        return jdbcTemplate.update("UPDATE board SET title = ?, content = ? WHERE id = ?",
                board.getTitle(), board.getContent(), board.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM board WHERE id = ?", id);
    }
}
