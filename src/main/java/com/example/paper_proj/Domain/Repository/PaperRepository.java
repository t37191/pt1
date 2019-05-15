package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper,Integer> {
    @Query(value = "from Paper u where u.title like %:titlekey%")
    List<Paper> findByTitle(@Param("titlekey")String titlekey);
}
