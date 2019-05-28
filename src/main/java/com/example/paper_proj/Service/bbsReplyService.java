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
    //通过帖子id获取本帖子的回复
    public List<bbsReply> getByTopicId(Integer topic_id){
        return replyRepository.getByTopicidOrderByReplytimeDesc(topic_id);
    }
//    //通过用户id和帖子id获取回复
//    public List<bbsReply> getByUser_idAndTopic_id(Integer user_id,Integer topic_id){
//        return replyRepository.getByUseridAndTopicidOrderByReplytime(user_id,topic_id);
//    }
    //通过回复的帖子id和回复的id来获取回复列表
    public List<bbsReply> getByR_TopicIdAndR_ReplyId(Integer topic_id, Integer r_reply_id){
        return replyRepository.getAllByTopicidAndRreplyidOrderByReplytimeDesc(topic_id,r_reply_id);
    }
    //获取回复数量
    public  Integer getReplyCountByR_reply_id(Integer r_reply_id){
        return replyRepository.countByRreplyid(r_reply_id);
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
