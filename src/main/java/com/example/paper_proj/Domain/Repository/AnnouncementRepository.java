package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
    List<Announcement> getAllByIdIsNotNullOrderByTimeDesc();
    Announcement getById(Integer id);
}
