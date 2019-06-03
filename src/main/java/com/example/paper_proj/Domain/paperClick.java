package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "paper_click")
public class paperClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Integer id;

    @Getter
    @Setter
    @Column(name = "paper_id")
    private String paperid;

    @Getter
    @Setter
    @Column(name = "click_cnt")
    private Integer clickcnt;

}
