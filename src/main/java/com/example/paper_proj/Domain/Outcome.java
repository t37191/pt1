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
//@Document(indexName = "aminer_v2", type = "_doc")
public class Outcome implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "outcome_id")
   // @Field(index = false, type = FieldType.Integer)
    protected Integer outcome_id;

    @Getter
    @Setter
    @Column(name = "title")
   // @Field(type = FieldType.Text , analyzer = "ik_max_word")
    protected String title;

    @Getter
    @Setter
    @Column(name = "abstract")
   // @Field(type = FieldType.Text , analyzer = "ik_max_word")
    protected String abstr;

    @Getter
    @Setter
    @Column(name = "pdf")
   // @Field(index = false, type = FieldType.Text)
    protected String pdf;

    @Getter
    @Setter
    @Column(name = "time")
   // @Field(type = FieldType.Date)
    protected Timestamp time;

//    @OneToMany(mappedBy = "outcome",cascade = CascadeType.ALL)
//    @Getter
//    @Setter
//    private Set<Keyword> keywords=new HashSet<>();
//
//    public void addKeywords(Keyword keyword){
//        keyword.setOutcome(this);
//        this.keywords.add(keyword);
//    }
}
