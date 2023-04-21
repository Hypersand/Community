package com.Together.Community.controller;

import com.Together.Community.domain.ReviewBoard;
import com.Together.Community.repository.ReviewBoardRepository;
import com.Together.Community.service.ReviewBoardService;
import com.Together.Community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviewboard")
@RequiredArgsConstructor
public class ReviewBoardController {

    private final ReviewBoardRepository reviewBoardRepository;

    private final ReviewBoardService reviewBoardService;

    private final UserService userService;


    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page=0, size = 5, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchKeyword) {
        Page<ReviewBoard> reviewBoardList = reviewBoardService.reviewBoardSearchList(searchKeyword,pageable);
        int startPage = Math.max(1,reviewBoardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(reviewBoardList.getTotalPages(), reviewBoardList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("reviewBoards", reviewBoardList);
        return "reviewboard/list";
    }

    // 게시글 작성
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("reviewboard", new ReviewBoard());
        }
        else {
            ReviewBoard reviewBoard = reviewBoardRepository.findById(id).orElse(null);
            model.addAttribute("reviewboard", reviewBoard);
        }
        return "reviewboard/form";
    }

    @PostMapping("/form")
    public String Submit(@ModelAttribute ReviewBoard reviewBoard) {
        reviewBoardService.form(reviewBoard);

        return "redirect:/reviewboard/list";
    }

    /// 글 상세 조회
    @GetMapping("/view")
    public String boardView(Model model, @RequestParam final Long id) {
        ReviewBoard reviewBoard = reviewBoardService.view(id);
        model.addAttribute("reviewboard",reviewBoard);
        return "reviewboard/view";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String boardDelete(Long id) {
        reviewBoardService.boardDelete(id);

        return "redirect:/reviewboard/list";
    }

//    // 게시글 수정
//    @GetMapping("/modify/{id}")
//    public String boardModify(@PathVariable("id") Long id, Model model) {
//
//        model.addAttribute("reviewboard", reviewBoardService.view(id));
//
//        return "reviewboard/edit";
//    }
//    @PostMapping("/update/{id}")
//    public String boardUpdate(@PathVariable("id") Long id, ReviewBoard board) {
//        ReviewBoard boardTemp = reviewBoardService.view(id);
//        boardTemp.setTitle(board.getTitle());
//        boardTemp.setContent(board.getContent());
//        boardTemp.setLatitude(board.getLatitude());
//        boardTemp.setLongitude(board.getLongitude());
//        boardTemp.setLocation(board.getLocation());
//        boardTemp.setUrl(board.getUrl());
//        boardTemp.setUrlpassword(board.getUrlpassword());
//        gatherBoardService.form(boardTemp);
//
//        return "redirect:/reviewboard/list";
//    }


}
