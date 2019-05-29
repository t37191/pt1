package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.bbsReply;
import com.example.paper_proj.Service.bbsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8000")
public class BBS_ReplyController {
    @Autowired
    private bbsReplyService replyService;

    private List<bbsReply> bytopicList;
    private List<bbsReply> byreplyList;

    @GetMapping(value="/getByUserId")
    public List<bbsReply> getReplyListByUserId(@RequestParam("user.id") Integer user_id){
        return replyService.getByUserId(user_id);
    }


//    @GetMapping(value="/bbs_reply")
//    public List<bbsReply> getReply(@RequestParam("user_id") Integer user_id,@RequestParam("r_topic_id") Integer topic_id ){
//        return replyService.getByUser_idAndTopic_id(user_id,topic_id);
//    }


    //获取某个回复的的回复数量
    @GetMapping(value = "/bbs_reply/reply_count")
    public int getRNumberByReplyId(@RequestParam("reply_id") Integer reply_id){
        byreplyList=replyService.getReplyByR_reply_id(reply_id);
        return byreplyList.size();
    }
    //通过回复id获取回复列表
    @GetMapping(value="/bbs_r_reply")
    public List<bbsReply> getReplyByR_reply_id(@RequestParam("r_topic_id") Integer r_topic_id,
                                                          @RequestParam("r_reply_id") Integer r_reply_id,
                                                          @RequestParam("_start")Integer start,
                                                          @RequestParam("_limit")Integer limit){
        if(start > byreplyList.size())
            return new ArrayList<>();
        return byreplyList.subList(start,Math.min(start+limit,byreplyList.size()));
    }


    //通过帖子获取回复列表
    @GetMapping(value="/bbs_topic_reply")
    public List<bbsReply> getReplyByTopic_idAndR_reply_id(
                                                          @RequestParam("_start")Integer start,
                                                          @RequestParam("_limit")Integer limit){
        if(start > bytopicList.size())
            return new ArrayList<>();
        return bytopicList.subList(start,Math.min(start+limit,bytopicList.size()));
    }
    //获取帖子的回复数量
    @GetMapping(value = "/bbs_reply/topic_count")
    public int getRNumberByTopicId(@RequestParam("topic_id") Integer topic_id){
        bytopicList=replyService.getReplyByTopic_id(topic_id,1);
        return bytopicList.size();
    }




    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public void deleteReply(@RequestParam("topic.id") Integer topic_id){
        replyService.deleteReply(topic_id);
    }

    @PostMapping(value="/add")
    public bbsReply addReply(@RequestBody bbsReply bbs_reply){
        return replyService.addReply(bbs_reply);
    }

    @PutMapping(value="/update")
    public bbsReply updateReply(@RequestBody bbsReply bbs_reply){
        return replyService.updateReply(bbs_reply);
    }
}
