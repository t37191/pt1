package com.example.paper_proj.Domain.ESIndexDomain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Pub implements Serializable {
    //论文id
    @Getter
    @Setter
    private String i;

    //第几作
    @Getter
    @Setter
    private Integer r;
}
