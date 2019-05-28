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

    @GetMapping(value="/getByUserId")
    public List<bbsReply> getReplyListByUserId(@RequestParam("user.id") Integer user_id){
        return replyService.getByUserId(user_id);
    }

    @GetMapping(value="/getByTopicId")
    public List<bbsReply> getReplyListByTopicId(@RequestParam("topic.id") Integer topic_id){
        return replyService.getByTopicId(topic_id);
    }

//    @GetMapping(value="/bbs_reply")
//    public List<bbsReply> getReply(@RequestParam("user_id") Integer user_id,@RequestParam("r_topic_id") Integer topic_id ){
//        return replyService.getByUser_idAndTopic_id(user_id,topic_id);
//    }

    //通过帖子id和回复的id获取回复列表
    @GetMapping(value="/bbs_reply")
    public List<bbsReply> getReplyByTopic_idAndR_reply_id(@RequestParam("r_topic_id") Integer r_topic_id,
                                                          @RequestParam("r_reply_id") Integer r_reply_id,
                                                          @RequestParam("_start")Integer start,
                                                          @RequestParam("_limit")Integer limit){
        List <bbsReply> bbsReplies=replyService.getByR_TopicIdAndR_ReplyId(r_topic_id,r_reply_id);
        if(start > bbsReplies.size())
            return new ArrayList<>();
        return bbsReplies.subList(start,Math.min(start+limit,bbsReplies.size()));
    }
    //获取某个回复的回复数量
    @GetMapping(value = "/bbs_reply/count")
    public Integer getRNumberByReplyId(@RequestParam("r_reply_id") Integer r_reply_id){
        return replyService.getReplyCountByR_reply_id(r_reply_id);
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
