package com.example.paper_proj.Domain.EmbeddedKey;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeywordPK implements Serializable {

    private String keyword;

    private Integer outcome_id;

    public String getKeyword(){
        return keyword;
    }

    public void setKeyword(String keyword){
        this.keyword=keyword;
    }

    public Integer getOutcome_id(){
        return outcome_id;
    }

    public void setOutcome_id(Integer outcome_id){
        this.outcome_id=outcome_id;
    }
}
