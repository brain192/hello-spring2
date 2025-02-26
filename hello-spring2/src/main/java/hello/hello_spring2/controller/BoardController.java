package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Board;
import hello.hello_spring2.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardsaa")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    /*
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public void createBoard(@RequestBody Board board) {
        boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody Board board) {
        board.setId(id);
        boardService.updateBoard(board);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

     */
}