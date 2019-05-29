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

    //通过用户id获取用户回复
    public List<bbsReply> getByUserId(Integer user_id){
        return replyRepository.getByUseridOrderByReplytimeDesc(user_id);
    }

//    //通过用户id和帖子id获取回复
//    public List<bbsReply> getByUser_idAndTopic_id(Integer user_id,Integer topic_id){
//        return replyRepository.getByUseridAndTopicidOrderByReplytime(user_id,topic_id);
//    }
    //通过帖子id来获取回复列表
    public List<bbsReply> getReplyByTopic_id(Integer topic_id,Integer reply_type){
        return replyRepository.getByTopicidAndAndReplytypeOrderByReplytimeDesc(topic_id,reply_type);
    }
    //获取某个回复下面的回复
    public List<bbsReply> getReplyByR_reply_id(Integer r_reply_id){
        return replyRepository.getByRreplyidOrderByReplytimeDesc(r_reply_id);
    }


    //删除回复
    public void deleteReply(Integer reply_id){
        replyRepository.deleteByReplyid(reply_id);
    }

    //添加回复
    public bbsReply addReply(bbsReply reply){
        reply.setReplytime(new Date());
        return  replyRepository.save(reply);
    }
    //修改回复
    public bbsReply updateReply(bbsReply reply){
        return  replyRepository.save(reply);
    }
}
