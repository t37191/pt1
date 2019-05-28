package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.bbsReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface bbsReplyRepository extends JpaRepository<bbsReply,Integer> {
    /*
    查询相关
     */
    //获取用户回复列表
    public List<bbsReply> getByUseridOrderByReplytimeDesc(Integer user_id);
    //获取帖子回复列表
    public List<bbsReply> getByTopicidOrderByReplytimeDesc(Integer topic_id);

    //通过回复的帖子id和回复的id来获取回复列表
    List<bbsReply> getAllByTopicidAndRreplyidOrderByReplytimeDesc(Integer topic_id, Integer r_reply_id);

    //获取某个回复下面的回复数量
    Integer countByRreplyid(Integer r_reply_id);

    //删除回复
    @Transactional
    @Modifying
    public void deleteByReplyid(Integer reply_id);

}
