package com.Together.Community.service;

import com.Together.Community.domain.ReviewBoard;
import com.Together.Community.repository.ReviewBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewBoardService {

    private final ReviewBoardRepository reviewBoardRepository;

    // 글 작성
    public void form(ReviewBoard reviewBoard) {
        reviewBoardRepository.save(reviewBoard);
    }

    //글 제목으로 검색
    public Page<ReviewBoard> reviewBoardSearchList(String searchKeyword, Pageable pageable) {

        return reviewBoardRepository.findByContentContaining(searchKeyword, pageable);
    }
    // 글 위치로 검색
//    public Page<ReviewBoard> reviewBoardSearchList1(String searchKeyword, Pageable pageable) {
//
//        return reviewBoardRepository.findByLocationContaining(searchKeyword, pageable);
//    }

    //  목록 불러오기
    public ReviewBoard view(Long id) {
        return reviewBoardRepository.findById(id).get();
    }

    public Page<ReviewBoard> reviewBoardList(Pageable pageable) {
        return reviewBoardRepository.findAll(pageable);
    }

    // 게시글 삭제
    public void boardDelete(Long id) {
        reviewBoardRepository.deleteById(id);
    }


}
