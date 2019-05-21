package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper,Integer> {

}
