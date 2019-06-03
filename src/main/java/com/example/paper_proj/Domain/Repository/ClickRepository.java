package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.paperClick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<paperClick,Integer> {

    public paperClick findByPaperid(String paper_id);

    public boolean existsByPaperid(String paper_id);
}
