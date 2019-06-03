package com.example.paper_proj.Service;


import com.example.paper_proj.Domain.Repository.bbsReplyRepository;
import com.example.paper_proj.Domain.bbsReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class bbsReplyService {
    @Autowired
    private bbsReplyRepository replyRepository;
    @Autowired
    private UserService userService;
//    //通过用户id获取用户回复
//    public List<bbsReply> getByUserId(Integer user_id){
//        return replyRepository.getByUseridOrderByReplytimeDesc(user_id);
//    }

    //通过论文id来获取回复列表
    public List<bbsReply> getReplyByOutcome_id(String outcome_id,Integer reply_type){
        return replyRepository.getByOutcomeidAndReplytypeOrderByReplytimeAsc(outcome_id,reply_type);
    }
    //获取某个回复下面的回复
    public List<bbsReply> getReplyByR_reply_id(Integer r_reply_id){
        return replyRepository.getByRreplyidOrderByReplytimeAsc(r_reply_id);
    }
    //根据回复ID获取回复者ID
    public Integer getUseridByReplyid(Integer reply_id){
        bbsReply temp=replyRepository.getByReplyid(reply_id);
        return temp.getUserid();
    }
    //根据用户ID获取用户名
    public String getUsernameByUserid(Integer userid){
        return userService.getById(userid).getUsername();
    }

//    //删除回复
//    public void deleteReply(Integer reply_id){
//        replyRepository.deleteByReplyid(reply_id);
//    }

    //添加回复
    public bbsReply addReply(bbsReply reply){
        reply.setReplytime(new Date());
        return  replyRepository.save(reply);
    }
//    //修改回复
//    public bbsReply updateReply(bbsReply reply){
//        return  replyRepository.save(reply);
//    }
}
