package hello.hello_spring2.service;

import hello.hello_spring2.domain.Board2;
import hello.hello_spring2.repository.BoardRepository2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService2 {
    private final BoardRepository2 boardRepository;

    public BoardService(BoardRepository2 boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board2> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board2 getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public void createBoard(Board2 board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board2 board) {
        boardRepository.update(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }
}
