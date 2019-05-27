package com.example.paper_proj.Domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Author implements Serializable {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
}
