package hello.hello_spring2.controller;


import hello.hello_spring2.domain.Board2;
import hello.hello_spring2.repository.BoardRepository2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/*

 */
@Controller
@RequestMapping("/boards")
public class BoardController2 {
    private final BoardRepository2 boardRepository;

    // 파일 저장 경로 (application.properties에서 주입)
    @Value("${file.upload-dir}")
    private String uploadDir;

    public BoardController2(BoardRepository2 boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 목록 페이지
    @GetMapping
    public String list(Model model) {
        List<Board2> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "list";
    }

    // 게시글 작성 폼
    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }

    // 게시글 등록 + 파일 업로드 처리
    @PostMapping
    public String create(@RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("file") MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String storedFilename = UUID.randomUUID() + "_" + originalFilename;

        Path path = Paths.get(uploadDir).resolve(storedFilename);
        Files.createDirectories(path.getParent());
        file.transferTo(path.toFile());

        Board2 board = new Board2();
        board.setTitle(title);
        board.setContent(content);
        board.setWriter("익명");
        board.setOriginalFilename(originalFilename);
        board.setFilename(storedFilename);

        boardRepository.save(board);
        return "redirect:/boards";
    }

    // 게시글 상세 보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board2 board = boardRepository.findById(id).orElseThrow();
        model.addAttribute("board", board);
        return "detail";
    }

    // 첨부파일 다운로드 처리
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Board2 board = boardRepository.findById(id).orElseThrow();

        Path path = Paths.get(uploadDir).resolve(board.getFilename());
        Resource resource = new UrlResource(path.toUri());

        // 파일 이름 인코딩 처리
        String encodedName = URLEncoder.encode(board.getOriginalFilename(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedName + "\"")
                .body(resource);
    }
}
