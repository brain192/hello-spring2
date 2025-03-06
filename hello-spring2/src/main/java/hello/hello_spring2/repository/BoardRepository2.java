package hello.hello_spring2.repository;

import hello.hello_spring2.domain.Board2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository2 {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시글 저장 (파일 포함)
    public int save(Board2 board) {
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        System.out.println(board.getFilename());
        //Incorrect string value 오류 발생 해결방법:테이블을 utf8로 변경
        return jdbcTemplate.update(
                "INSERT INTO board (title, content, filename) VALUES (?, ?, ?)",
                board.getTitle(), board.getContent(), board.getFilename());
    }



    // 특정 게시글 조회 (파일 다운로드 시 필요)
    public Board2 findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM board WHERE id = ?",
                (rs, rowNum) -> new Board2(
                        rs.getLong("id"), rs.getString("title"),
                        rs.getString("content"), rs.getString("filename")),
                id);
    }

    private final RowMapper<Board2> boardRowMapper = (rs, rowNum) ->
            new Board2(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("filename"));



    public List<Board2> findAll() {
        return jdbcTemplate.query("SELECT * FROM board ORDER BY id DESC", boardRowMapper);
    }


    /*
    public Board2 findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM board WHERE id = ?", boardRowMapper, id);
    }

    public int save(Board2 board) {
        return jdbcTemplate.update("INSERT INTO board (title, content) VALUES (?, ?)",
                board.getTitle(), board.getContent());
    }

     */


    public int update(Board2 board) {
        return jdbcTemplate.update("UPDATE board SET title = ?, content = ? WHERE id = ?",
                board.getTitle(), board.getContent(), board.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM board WHERE id = ?", id);
    }
}
