package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "project")
@PrimaryKeyJoinColumn(name = "outcome_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Project extends Outcome {
    @Getter
    @Setter
    @Column(name = "state")
    private String state;
}
