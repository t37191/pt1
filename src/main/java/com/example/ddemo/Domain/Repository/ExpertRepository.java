package com.example.ddemo.Domain.Repository;

import com.example.ddemo.Domain.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    public Expert findByUsername(String username);
}
