package com.Together.Community.Domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "GatherBoard")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class GatherBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Double latitude;
    private Double longitude;
    private String location;

    @CreationTimestamp
    private LocalDateTime created;
    private String userid;
    private String url;
    private String urlpassword;
    private Integer statuscode;

    @Builder
    public GatherBoard(Long id, String title, String content, Double latitude, Double longitude,
                       String location, LocalDateTime created, String userid, String url, String urlpassword, Integer statuscode)
    {
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
