package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bbs_reply")
@Data
public class bbsReply {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    @Column(name="reply_id")
    private Integer replyid;

    @Setter
    @Getter
    @Column(name = "user_id")
    private Integer userid;

    @Setter
    @Getter
    @Column(name = "r_outcome_id")
    private String outcomeid;

    @Setter
    @Getter
    @Column(name = "reply_content")
    private String replycontent;

    @Setter
    @Column(name = "reply_time")
    private Date replytime;

    @Setter
    @Getter
    @Column(name="r_reply_id")
    private Integer rreplyid;

    @Setter
    @Getter
    @Column(name="reply_type")
    private Integer replytype;

    public String getReplytime(){
        String temp=this.replytime.toString().replace(".0","");
        return temp;
    }
}
