package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Paper;
import com.example.paper_proj.Domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query(value = "from Project u where u.title like %:titlekey%")
    List<Project> findByTitle(@Param("titlekey")String titlekey);
}
