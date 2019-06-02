package com.example.paper_proj.Domain.ESIndexDomain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Tag implements Serializable {
    @Getter
    @Setter
    private String t;

    @Getter
    @Setter
    private Integer w;
}
