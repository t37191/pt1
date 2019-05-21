package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "keywords")
@Data
public class Keyword {
    @Id
    @Getter
    @Setter
    @Column(name = "keyword_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer keyword_id;

    @Getter
    @Setter
    @Column(name = "keyword")
    private String keyword;

    @Getter
    @Setter
    @Column(name = "outcome_id")
    private Integer outcome_id;

}
