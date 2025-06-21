package hello.hello_spring2.controller;

import hello.hello_spring2.domain.Board3;
import hello.hello_spring2.service.BoardService3;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public String writeForm(Model model, HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/logins"; // 로그인 안 했으면 로그인 페이지로
        }
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
    public String detail(@PathVariable("id") Long id, Model model) {
        Board3 board = boardService.get(id);
        model.addAttribute("board", board);
        return "detail"; // detail.html로 이동
    }

    /**
     * 파일 다운로드 처리
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") Long id, HttpSession session) throws Exception {
        Board3 board = boardService.get(id);
        File file = boardService.getFile(board.getFilename());
        if (session.getAttribute("loginMember") == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 로그인 안되도록 방지
            /*
            HttpStatus.FORBIDDEN은 웹 개발에서 자주 등장하는 HTTP 상태 코드입니다.
            Spring Boot에서도 이를 통해 접근 거부(권한 없음) 상황을 명확하게 처리할 수 있습니다.
             */
        }
        // 파일 다운로드를 위한 Resource 생성
        Resource resource = new FileSystemResource(file);
        String encodedFilename = URLEncoder.encode(board.getOriginalFilename(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .body(resource);
    }
}
