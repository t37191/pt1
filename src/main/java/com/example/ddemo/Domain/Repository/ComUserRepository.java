package com.example.ddemo.Domain.Repository;

import com.example.ddemo.Domain.ComUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComUserRepository extends JpaRepository<ComUser,Integer> {

    public ComUser findByUsername(String username);

}
