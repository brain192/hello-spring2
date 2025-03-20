package hello.hello_spring2.controller;


import hello.hello_spring2.domain.Board2;
import hello.hello_spring2.service.BoardService2;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*

 */
@Controller
@RequestMapping("/boards")
public class BoardController2 {
    private final BoardService2 boardService;

    public BoardController2(BoardService2 boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String list(Model model) {
        /*
        리스트타입
         */
        List<Board2> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "list";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     * PathVariable 이란? 경로 변수를 표시하기 위해 메서드에 매개변수에 사용
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Board2 board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "detail";
    }

    /**
     * 게시판 작성폼
     * @return
     */
    @GetMapping("/new")
    public String newForm() {
        return "new";
    }

    /**
     * 📌 게시글 저장 처리 (파일 업로드 포함)
     */
    @PostMapping
    public String create(@ModelAttribute Board2 board, @RequestParam("file") MultipartFile file) throws IOException {
        boardService.createBoard(board, file);
        return "redirect:/boards";
    }

    /**
     * 📌 게시글 수정
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Board2 board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "edit";
    }

    /**
     * 📌 게시글 수정 처리 (파일 업로드 포함)
     */
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute Board2 board) {
        board.setId(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    /**
     * 📌 게시글 삭제 처리
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }

    // 파일 다운로드 //현재 잘 되지 않음
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws MalformedURLException {
        Board2 board = boardService.getBoardById(id);
        if (board.getFilename() == null) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get("uploads/").resolve(board.getFilename()).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + board.getFilename() + "\"")
                .body(resource);
    }
}
