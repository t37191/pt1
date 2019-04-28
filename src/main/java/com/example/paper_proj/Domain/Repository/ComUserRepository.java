package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.ComUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComUserRepository extends JpaRepository<ComUser,Integer> {

    public ComUser findByUsername(String username);

}
