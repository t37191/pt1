package com.example.paper_proj.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bbs_topic")
public class Topic {
    @Id
    @GeneratedValue

    @Getter
    @Setter
    @Column(name="topic_id")
    private int topicId;

    @Getter
    @Setter
    @Column(name="section_id")
    private int sectionId;

    @Getter
    @Setter
    @Column(name="topic_name")
    private String topicName;


}
