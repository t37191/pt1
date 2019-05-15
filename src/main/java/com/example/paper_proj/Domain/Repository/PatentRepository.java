package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatentRepository extends JpaRepository<Patent,Integer> {
    @Query(value = "from Patent u where u.title like %:titlekey%")
    List<Patent> findByTitle(@Param("titlekey")String titlekey);
}
