package com.example.paper_proj.Domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="bbs_section")
@Data
public class bbsSection {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    @Column(name="section_id")
    private Integer sectionid;

    @Setter
    @Getter
    @Column(name = "section_theme")
    private String theme;

    @Setter
    @Getter
    @Column(name = "section_name")
    private String name;

    @Setter
    @Getter
    @Column(name = "section_master_id")
    private Integer masterid;
}
