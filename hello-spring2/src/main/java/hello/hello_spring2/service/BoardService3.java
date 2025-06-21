package hello.hello_spring2.service;

import hello.hello_spring2.domain.Board3;
import hello.hello_spring2.repository.BoardRepository3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 게시판 서비스 계층
 * 비즈니스 로직 (파일 저장 등)을 처리
 */
@Service
public class BoardService3 {
    private final BoardRepository3 boardRepository;

    // 파일 저장 경로 설정 (서버 프로젝트 루트 하위 uploads 폴더)
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    public BoardService3(BoardRepository3 boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시글 저장 + 파일 업로드 처리
     */
    public void write(Board3 board, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // UUID를 이용한 고유 파일명 생성
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String filename = uuid + "_" + originalFilename;

            // 업로드 디렉토리 생성 (없을 시)
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();//경로 없을시 생성

            // 실제 파일 저장
            File saveFile = new File(uploadDir, filename);
            file.transferTo(saveFile);

            // board 객체에 파일 정보 저장
            board.setFilename(filename);//파일이름저장
            board.setOriginalFilename(originalFilename);//원본 파일이름 저장
        }

        // DB 저장
        boardRepository.save(board);
    }

    /**
     * 게시글 상세 조회
     */
    public Board3 get(Long id) {
        return boardRepository.findById(id);
    }

    /**
     * 게시글 전체 조회
     */
    public List<Board3> list() {
        return boardRepository.findAll();
    }

    /**
     * 다운로드할 파일 객체 반환
     */
    public File getFile(String filename) {
        return new File(uploadDir, filename);
    }
}
