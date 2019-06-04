package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Announcement;
import com.example.paper_proj.Service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8000")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    private List<Announcement> announcementList;

    //获取所有公告的数量
    @GetMapping(value = "/announcement/count")
    public int getAnnouncementNumber(){
        announcementList=announcementService.getAllAnnouncement();
        return announcementList.size();
    }
    //获取公告列表
    @GetMapping(value = "/announcement")
    public List<Announcement> getAllAnnouncement(@RequestParam("_start")Integer start,
                                                 @RequestParam("_limit")Integer limit){
        return announcementList.subList(start,Math.min(start+limit,announcementList.size()));
    }
    //根据公告ID获取某条公告
    @GetMapping(value = "/announcement/1")
    public Announcement getAnnouncementById(@RequestParam("announcement_id")Integer id){
        return announcementService.getAnnouncementById(id);
    }
    //增加公告
    @PostMapping(value="/announcement")
    @PreAuthorize("hasAuthority('admin')")
    public Announcement addAnnouncement(@RequestBody Announcement announcement){
        return announcementService.addAnnouncement(announcement);
    }
}
