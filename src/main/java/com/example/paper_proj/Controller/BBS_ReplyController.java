package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.bbsReply;
import com.example.paper_proj.Service.bbsReplyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8000")
public class BBS_ReplyController {
    @Autowired
    private bbsReplyService replyService;

    private List<bbsReply> replyList;
    private JSONArray list;
//    @GetMapping(value="/getByUserId")
//    public List<bbsReply> getReplyListByUserId(@RequestParam("user.id") Integer user_id){
//        return replyService.getByUserId(user_id);
//    }


    //获取某个论文或者某个回复的回复数量，reply_id=0则代表获取某个论文的回复数
    @GetMapping(value = "/bbs_reply/count")
    public int getReplyNumber(@RequestParam("r_outcome_id")String r_outcome_id,@RequestParam("reply_id") Integer reply_id){
        if(reply_id==0){
            replyList=replyService.getReplyByOutcome_id(r_outcome_id,0);
        }else{
            replyList=replyService.getReplyByR_reply_id(reply_id);
        }
        return replyList.size();
    }
    //获取回复列表
    @GetMapping(value="/bbs_reply")
    public String getReply(@RequestParam("r_outcome_id")String r_outcome_id,
            @RequestParam("reply_id") Integer reply_id,
            @RequestParam("_start")Integer start,
            @RequestParam("_limit")Integer limit)throws IOException {
        JSONArray jsonArray = new JSONArray();
        if(start > replyList.size())
            return jsonArray.toString();
        for(bbsReply temp:replyList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("reply_id",(Integer)temp.getReplyid());
            jsonObject.put("user_id",(Integer)temp.getUserid());
            jsonObject.put("user_name",(String)replyService.getUsernameByUserid(temp.getUserid()));
            jsonObject.put("r_outcome_id",(String)temp.getOutcomeid());
            jsonObject.put("reply_content",(String)temp.getReplycontent());
            jsonObject.put("r_reply_id",(Integer)temp.getRreplyid());
            jsonObject.put("reply_time",(String)temp.getReplytime());
            if(reply_id==0){
                jsonObject.put("r_reply_user_name", (String) null);
                jsonObject.put("r_reply_user_id", (Integer) null);
            }else {
                Integer user_id = replyService.getUseridByReplyid(temp.getRreplyid());
                jsonObject.put("r_reply_user_name", (String) replyService.getUsernameByUserid(user_id));
                jsonObject.put("r_reply_user_id", (Integer) user_id);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.subList(start,Math.min(start+limit,replyList.size())).toString();
    }

//    //根据回复ID获取单条回复
//    @GetMapping(value = "/bbs_reply/1")
//    public String getReplyByReplyId(@RequestParam("reply_id")Integer id){
//        bbsReply reply=replyService.getReplyByReplyid(id);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("reply_id",(Integer)reply.getReplyid());
//        jsonObject.put("user_id",(Integer)reply.getUserid());
//        jsonObject.put("user_name",(String)replyService.getUsernameByUserid(temp.getUserid()));
//        jsonObject.put("r_outcome_id",(String)temp.getOutcomeid());
//        jsonObject.put("reply_content",(String)temp.getReplycontent());
//        jsonObject.put("r_reply_id",(Integer)temp.getRreplyid());
//        jsonObject.put("reply_time",(String)temp.getReplytime());
//        if(reply_id==0){
//            jsonObject.put("r_reply_user_name", (String) null);
//            jsonObject.put("r_reply_user_id", (Integer) null);
//        }else {
//            Integer user_id = replyService.getUseridByReplyid(temp.getRreplyid());
//            jsonObject.put("r_reply_user_name", (String) replyService.getUsernameByUserid(user_id));
//            jsonObject.put("r_reply_user_id", (Integer) user_id);
//        }
//    }
//    //通过帖子获取回复列表
//    @GetMapping(value="/bbs_topic_reply")
//    public List<bbsReply> getReplyByTopic_idAndR_reply_id(
//                                                          @RequestParam("_start")Integer start,
//                                                          @RequestParam("_limit")Integer limit){
//        if(start > bytopicList.size())
//            return new ArrayList<>();
//        return bytopicList.subList(start,Math.min(start+limit,bytopicList.size()));
//    }
//    //获取帖子的回复数量
//    @GetMapping(value = "/bbs_reply/topic_count")
//    public int getRNumberByTopicId(@RequestParam("topic_id") Integer topic_id){
//        bytopicList=replyService.getReplyByTopic_id(topic_id,1);
//        return bytopicList.size();
//    }

//    @DeleteMapping(value = "/bbs_reply")
//    @PreAuthorize("hasAnyAuthority('admin','user')")
//    public void deleteReply(@RequestParam("reply_id") Integer reply_id){
//        replyService.deleteReply(reply_id);
//    }

    @PostMapping(value="/bbs_reply")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public bbsReply addReply(@RequestBody bbsReply bbs_reply){
        return replyService.addReply(bbs_reply);
    }

//    @PutMapping(value="/update")
//    public bbsReply updateReply(@RequestBody bbsReply bbs_reply){
//        return replyService.updateReply(bbs_reply);
//    }
}
