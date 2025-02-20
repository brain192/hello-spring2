package hello.hello_spring2.controller;


import hello.hello_spring2.domain.Board2;
import hello.hello_spring2.service.BoardService2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController2 {
    private final BoardService2 boardService;

    public BoardController2(BoardService2 boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String list(Model model) {
        List<Board2> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board2 board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("/new")
    public String newForm() {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute Board2 board) {
        boardService.createBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Board2 board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute Board2 board) {
        board.setId(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}
