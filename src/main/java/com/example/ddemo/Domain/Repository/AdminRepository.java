package com.example.ddemo.Domain.Repository;

import com.example.ddemo.Domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    public Admin findByUsername(String username);

}
