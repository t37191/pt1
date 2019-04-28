package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    public Expert findByUsername(String username);
}
