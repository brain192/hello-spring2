package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Board3;
import hello.hello_spring2.service.BoardService3;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class BoardController3 {
    private final BoardService3 boardService;

    public BoardController3(BoardService3 boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시글 목록 화면
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Board3> boards = boardService.list();
        model.addAttribute("boards", boards);
        return "list"; // list.html로 이동
    }

    /**
     * 게시글 작성 폼
     */
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board3());
        return "write";
    }

    /**
     * 게시글 등록 처리
     */
    @PostMapping("/write")
    public String write(@ModelAttribute Board3 board, @RequestParam("file") MultipartFile file) throws Exception {
        boardService.write(board, file);
        return "redirect:/list";
    }

    /**
     * 게시글 상세 보기
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board3 board = boardService.get(id);
        model.addAttribute("board", board);
        return "detail"; // detail.html로 이동
    }

    /**
     * 파일 다운로드 처리
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws Exception {
        Board3 board = boardService.get(id);
        File file = boardService.getFile(board.getFilename());

        // 파일 다운로드를 위한 Resource 생성
        Resource resource = new FileSystemResource(file);
        String encodedFilename = URLEncoder.encode(board.getOriginalFilename(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .body(resource);
    }
}
