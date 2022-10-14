package com.Together.Community.Controller;


import com.Together.Community.Domain.GatherBoard;
import com.Together.Community.Repository.GatherBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gatherboard")
public class GatherBoardController {

    @Autowired
    private GatherBoardRepository gatherBoardRepository;


    @GetMapping("/list")
    public String list(Model model) {
        List<GatherBoard> gatherBoards = gatherBoardRepository.findAll();
        model.addAttribute("gatherBoards", gatherBoards);
        return "gatherboard/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("gatherboard", new GatherBoard());
        }
        else {
            GatherBoard gatherBoard = gatherBoardRepository.findById(id).orElse(null);
            model.addAttribute("gatherboard", gatherBoard);
        }
        return "gatherboard/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute GatherBoard gatherBoard) {
        gatherBoardRepository.save(gatherBoard);
        return "redirect:/gatherboard/list";
    }

}
