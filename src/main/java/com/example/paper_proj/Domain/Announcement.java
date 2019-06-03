package com.example.paper_proj.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="announcement")
public class Announcement {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    @Column(name="announcement_id")
    private Integer id;

    @Setter
    @Getter
    @Column(name = "title")
    private String title;

    @Setter
    @Getter
    @Column(name = "content")
    private String content;

    @Setter
    @Getter
    @Column(name = "time")
    private Date time;
}
