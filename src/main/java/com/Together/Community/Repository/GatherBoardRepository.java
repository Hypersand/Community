package com.Together.Community.Repository;

import com.Together.Community.Domain.GatherBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherBoardRepository extends JpaRepository<GatherBoard,Long> {


}
