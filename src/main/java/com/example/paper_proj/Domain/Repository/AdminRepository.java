package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    public Admin findByUsername(String username);

}
