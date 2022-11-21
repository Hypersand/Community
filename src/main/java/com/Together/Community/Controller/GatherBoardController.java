package com.Together.Community.Controller;


import com.Together.Community.Domain.GatherBoard;
import com.Together.Community.Repository.GatherBoardRepository;
import com.Together.Community.Service.GatherBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Queue;

@Controller
@RequestMapping("/gatherboard")
public class GatherBoardController {

    @Autowired
    private GatherBoardRepository gatherBoardRepository;

    private final GatherBoardService gatherBoardService;

    public GatherBoardController(GatherBoardService gatherBoardService) {
        this.gatherBoardService = gatherBoardService;
    }


    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model,@PageableDefault(page=0, size = 5, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,@RequestParam(required = false, defaultValue = "") String searchKeyword) {
        Page<GatherBoard> gatherBoardList = gatherBoardService.gatherBoardSearchList1(searchKeyword,pageable);
        int startPage = Math.max(1,gatherBoardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(gatherBoardList.getTotalPages(), gatherBoardList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("gatherBoards", gatherBoardList);
        return "gatherboard/list";
    }

    // 게시글 작성
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
    public String Submit(@ModelAttribute GatherBoard gatherBoard) {
        gatherBoardService.form(gatherBoard);

        return "redirect:/gatherboard/list";
    }

    /// 글 상세 조회
    @GetMapping("/view")
    public String boardView(Model model, @RequestParam final Long id) {
        GatherBoard gatherBoard = gatherBoardService.view(id);
        model.addAttribute("gatherBoard",gatherBoard);
        return "gatherboard/view";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String boardDelete(Long id) {
        gatherBoardService.boardDelete(id);

        return "redirect:/gatherboard/list";
    }

    // 게시글 수정
    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model) {

        model.addAttribute("gatherBoard", gatherBoardService.view(id));

        return "gatherBoard/edit";
    }
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, GatherBoard board) {
        GatherBoard boardTemp = gatherBoardService.view(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardTemp.setLatitude(board.getLatitude());
        boardTemp.setLongitude(board.getLongitude());
        boardTemp.setLocation(board.getLocation());
        boardTemp.setUrl(board.getUrl());
        boardTemp.setUrlpassword(board.getUrlpassword());
        gatherBoardService.form(boardTemp);

        return "redirect:/gatherboard/list";
    }




}
