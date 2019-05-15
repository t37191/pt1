package com.example.paper_proj.Domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "outcome")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Outcome implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "outcome_id")
    protected Integer outcome_id;

    @Getter
    @Setter
    @Column(name = "title")
    protected String title;

    @Getter
    @Setter
    @Column(name = "abstract")
    protected String abstr;

    @Getter
    @Setter
    @Column(name = "content")
    protected Byte[] content;

    @Getter
    @Setter
    @Column(name = "time")
    protected Timestamp time;
}
