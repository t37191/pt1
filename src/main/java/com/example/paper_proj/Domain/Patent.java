package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "patent")
@PrimaryKeyJoinColumn(name = "outcome_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Patent extends Outcome {
    @Getter
    @Setter
    @Column(name = "patent_id")
    private Integer patent_id;
}
