package com.example.paper_proj.Domain;

import com.example.paper_proj.Domain.EmbeddedKey.KeywordPK;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "keywords")
@Data
public class Keyword {
    @EmbeddedId
    @Getter
    @Setter
    private KeywordPK keywordPK;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinColumn(name = "outcome_id")
//    private Outcome outcome;
}
