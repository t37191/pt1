package com.example.paper_proj.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {
    @Setter
    @Getter
    @Column(name = "authority")
    private Integer authority;
}
