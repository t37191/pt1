package com.example.paper_proj.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User implements Serializable, UserDetails {

    @Setter
    @Getter
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer user_id;

    @Setter
    @Getter
    @Column(name = "username")
    protected String username;

    @Setter
    @Getter
    @Column(name = "password")
    protected String password;

    @Setter
    @Getter
    @Column(name = "email")
    protected String email;

    @Setter
    @Getter
    @Column(name = "age")
    protected Integer age;

    @Setter
    @Getter
    @Column(name = "sex")
    protected String sex;

    @Setter
    @Getter
    @Column(name="auth")
    protected String auth;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> l=new ArrayList<>();
        l.add(new SimpleGrantedAuthority(this.getAuth()));
        return l;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled(){
        return true;
    }

}
