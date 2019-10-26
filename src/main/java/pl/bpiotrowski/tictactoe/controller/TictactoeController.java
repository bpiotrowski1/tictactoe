package pl.bpiotrowski.tictactoe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bpiotrowski.tictactoe.entity.Field;
import pl.bpiotrowski.tictactoe.entity.Status;
import pl.bpiotrowski.tictactoe.service.TictactoeService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class TictactoeController {

    private final TictactoeService tictactoeService;

    @GetMapping
    public String start(HttpSession session, Model model) {
        if(session.getAttribute("started") == null) {
            session.setAttribute("started", "true");
            tictactoeService.fillBoard();
        }
        addAttributesToModel(model);
        return "tictactoe";
    }

    @PostMapping
    public String create(@RequestBody Field field) {
        tictactoeService.create(field);
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
