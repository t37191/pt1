package com.example.paper_proj.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Expert")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
public class Expert extends User {
    @Setter
    @Getter
    @Column(name = "dep")
    private String dep;

    @Setter
    @Getter
    @Column(name = "post")
    private String post;

    public Expert(User user,String dep,String post) {
        this.setUser_id(user.getUser_id());
        this.setAge(user.getAge());
        this.setContact(user.getContact());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
        this.setDep(dep);
        this.setPost(post);
    };
}
