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
//    //获取用户回复列表
//    public List<bbsReply> getByUseridOrderByReplytimeDesc(Integer user_id);


    //通过论文获取所有回复
    List<bbsReply> getByOutcomeidAndReplytypeOrderByReplytimeAsc(String outcome_id,Integer reply_type);

    //获取某个回复下面的回复
    List<bbsReply> getByRreplyidOrderByReplytimeAsc(Integer r_reply_id);
    //通过回复ID获取回复者的用户ID
    bbsReply getByReplyid(Integer reply_id);
    //删除回复
    @Transactional
    @Modifying
    public void deleteByReplyid(Integer reply_id);

}
