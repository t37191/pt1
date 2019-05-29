package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Favorites;
import com.example.paper_proj.Domain.Topic;
import com.example.paper_proj.Service.BbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class BbsController {

    @Autowired
    private BbsService bbsService;

    private List<Topic> topicList;

    //获取当前Section的所有topic
    @GetMapping(value="/bbs_topic")
    public List<Topic> getAllTopicsInThisSection(
            @RequestParam("_start")Integer start,
            @RequestParam("_limit")Integer limit){
        if(start > topicList.size())
            return new ArrayList<>();
        return topicList.subList(start,Math.min(start+limit,topicList.size()));
    }
    //获取帖子的数量
    @GetMapping(value="/bbs_topic/total_count")
    public int getTopicNumber(@RequestParam("section_id")Integer section_id){
        topicList=bbsService.getAllTopicsBySectionId(section_id);
        return topicList.size();
    }







    //发表新topic
    @PostMapping("/create")
    public Topic createNewTopic(@RequestBody Topic topic){
        return bbsService.createNewTopic(topic);
    }

    //删除topic
    @DeleteMapping("/deletetopic")
    public void deleteTopic(@RequestParam("topic.id") int topicId){
        bbsService.deleteTopic(topicId);
    }

    /*//查看我点赞的topic
    @GetMapping("/myfavorites")
    public List<Favorites> myFavorites(@RequestParam("user.id") int userId){
        return bbsService.findFavoritesByUserId(userId);
    }*/
    //查看我点赞的topic
    @GetMapping("/myfavorites")
    public List<String> myFavorites(@RequestParam("user.id") int userId){
        return bbsService.findFavoritesByUserId(userId);
    }

    //点赞topic
    @PostMapping("/createfavorite")
    public Favorites createNewFavorite(@RequestBody Favorites favorites){
        return bbsService.createNewFavorite(favorites);
    }

    //取消点赞topic
    @DeleteMapping("/deletefavorite")
    public void deleteFavorite(@RequestParam("user.id") int userId,@RequestParam("topic.id") int topicId){
        bbsService.deleteFavorite(userId,topicId);
    }




}
