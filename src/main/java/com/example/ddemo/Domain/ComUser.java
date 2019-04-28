package com.example.ddemo.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "com_user")
@PrimaryKeyJoinColumn(name = "user_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class ComUser extends User {
    @Setter
    @Getter
    @Column(name = "point")
    protected Integer point;

}
