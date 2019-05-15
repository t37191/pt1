package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "paper")
@PrimaryKeyJoinColumn(name = "outcome_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Paper extends Outcome {
    @Getter
    @Setter
    @Column(name = "price")
    private String price;

    @Getter
    @Setter
    @Column(name = "belong")
    private String belong;
}
