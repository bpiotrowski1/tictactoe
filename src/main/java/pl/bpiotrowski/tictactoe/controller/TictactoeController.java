package pl.bpiotrowski.tictactoe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.bpiotrowski.tictactoe.entity.Status;
import pl.bpiotrowski.tictactoe.service.TictactoeService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class TictactoeController {

    private final TictactoeService tictactoeService;

    @GetMapping("/")
    public String start(HttpSession session, Model model) {
        if(session.getAttribute("started")!= null && tictactoeService.checkWin()) {
            session.setAttribute("win", "true");
            model.addAttribute("win",true);
        }
        if(session.getAttribute("started") == null) {
            session.setAttribute("started", "true");
            tictactoeService.fillBoard();
        }
        addAttributesToModel(model);
        return "tictactoe";
    }

    @GetMapping("/change/{id}")
    public String changeStatus(@PathVariable Long id, HttpSession session) {
        if(session.getAttribute("win") == null || !session.getAttribute("win").equals("true")) {
            tictactoeService.changeStatus(id);
        }
        return "redirect:/";
    }

    @GetMapping("/new-game")
    public String startNewGame(HttpSession session) {
        tictactoeService.resetBoard();
        session.removeAttribute("win");
        return "redirect:/";
    }

    private void addAttributesToModel(Model model) {
        model.addAttribute("status_x", Status.X);
        model.addAttribute("status_o", Status.O);
        model.addAttribute("row1", tictactoeService.findAllByRow(1));
        model.addAttribute("row2", tictactoeService.findAllByRow(2));
        model.addAttribute("row3", tictactoeService.findAllByRow(3));
    }
}
