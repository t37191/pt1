package com.example.paper_proj.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "bbs_favorites")
public class Favorites {
    @Id
    @GeneratedValue

    @Getter
    @Setter
    @Autowired
    @Column(name="fav_id")
    private int favId;

    @Getter
    @Setter
    @Autowired
    @Column(name="user_id")
    private int userId;

    @Getter
    @Setter
    @Autowired
    @Column(name="topic_id")
    private int topicId;

}
