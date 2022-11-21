package com.Together.Community.Service;

import com.Together.Community.Domain.GatherBoard;
import com.Together.Community.Repository.GatherBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatherBoardService {


    private final GatherBoardRepository gatherBoardRepository;

    @Autowired
    public GatherBoardService(GatherBoardRepository gatherBoardRepository) {
        this.gatherBoardRepository = gatherBoardRepository;
    }

    // 글 작성
    public void form(GatherBoard gatherBoard) {
        gatherBoardRepository.save(gatherBoard);
    }

    //글 제목으로 검색
    public Page<GatherBoard> gatherBoardSearchList(String searchKeyword, Pageable pageable) {

        return gatherBoardRepository.findByTitleContaining(searchKeyword, pageable);
    }
    // 글 위치로 검색
    public Page<GatherBoard> gatherBoardSearchList1(String searchKeyword, Pageable pageable) {

        return gatherBoardRepository.findByLocationContaining(searchKeyword, pageable);
    }

    //  목록 불러오기
    public GatherBoard view(Long id) {
        return gatherBoardRepository.findById(id).get();
    }

    public Page<GatherBoard> gatherBoardList(Pageable pageable) {
        return gatherBoardRepository.findAll(pageable);
    }

    // 게시글 삭제
    public void boardDelete(Long id) {
        gatherBoardRepository.deleteById(id);
    }

    // 위치 비교하기







}

