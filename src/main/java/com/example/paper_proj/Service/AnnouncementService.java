package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Announcement;
import com.example.paper_proj.Domain.Repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    //获取全部公告
    public List<Announcement> getAllAnnouncement() {
        return announcementRepository.getAllByIdIsNotNullOrderByTimeDesc();
    }

    //增加公告
    public Announcement addAnnouncement(Announcement announcement) {
        announcement.setTime(new Date());
        return announcementRepository.save(announcement);
    }
    //通过公告ID查看公告
    public Announcement getAnnouncementById(Integer id){
        return announcementRepository.getById(id);
    }
}
