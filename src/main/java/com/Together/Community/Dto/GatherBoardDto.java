package com.Together.Community.Dto;


import com.Together.Community.Domain.GatherBoard;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GatherBoardDto {
    private Long id;
    private String title;
    private String created;
    private String userid;

    public GatherBoard toEntity() {
        GatherBoard board = GatherBoard.builder()
                .id(id)
                .title(title)
                .created(created)
                .userid(userid)
                .build();
        return board;
    }

    @Builder

    public GatherBoardDto(Long id, String title, String created, String userid) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.userid = userid;
    }
}
