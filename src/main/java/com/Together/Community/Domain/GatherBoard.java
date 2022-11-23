package com.Together.Community.Domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "GatherBoard")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class GatherBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GatherBoard_id")
    private Long id;
    @Column(name = "GatherBoard_title")
    private String title;
    @Column(name = "GatherBoard_content")
    private String content;
    @Column(name = "GatherBoard_latitude")
    private String latitude;
    @Column(name = "GatherBoard_longitude")
    private String longitude;
    @Column(name = "GatherBoard_location")
    private String location;

    @CreatedDate
    @Column(name = "GatherBoard_created")
    private String created = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    @Column(name = "GatherBoard_userid")
    private String userid;
    @Column(name = "GatherBoard_url")
    private String url;
    @Column(name = "GatherBoard_urlpassword")
    private String urlpassword;
    @Column(name = "GatherBoard_statuscode")
    private Integer statuscode;

    @OneToMany
    @JoinColumn(name = "GatherBoard_id")
    private List<ReviewBoard> reviewBoards = new ArrayList<>();


    @Builder

    public GatherBoard(Long id, String title, String content, String latitude, String longitude,
                       String location, String created, String userid, String url, String urlpassword, Integer statuscode) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.created = created;
        this.userid = userid;
        this.url = url;
        this.urlpassword = urlpassword;
        this.statuscode = statuscode;
    }
}
