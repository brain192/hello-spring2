package hello.hello_spring2.service;

import hello.hello_spring2.domain.Board;
import hello.hello_spring2.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        boardRepository.update(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }
}
