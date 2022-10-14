package com.Together.Community.Service;

import com.Together.Community.Repository.GatherBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatherBoardService {


    private final GatherBoardRepository gatherBoardRepository;

    @Autowired
    public GatherBoardService(GatherBoardRepository gatherBoardRepository) {
        this.gatherBoardRepository = gatherBoardRepository;
    }
    
}
