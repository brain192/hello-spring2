package hello.hello_spring2.service;

import hello.hello_spring2.domain.Board2;
import hello.hello_spring2.repository.BoardRepository2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BoardService2 {
    private final BoardRepository2 boardRepository;

    public BoardService2(BoardRepository2 boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 저장 (파일 업로드 포함)
    public void createBoard(Board2 board, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir = "uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // 업로드 폴더가 없으면 생성
            }

            // 파일 저장
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(Paths.get(filePath));
            board.setFilename(file.getOriginalFilename()); // DB에 저장할 파일명 설정
        }

        boardRepository.save(board);
    }

    public List<Board2> getAllBoards() {
        return boardRepository.findAll();
    }



    // 특정 게시글 조회
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
