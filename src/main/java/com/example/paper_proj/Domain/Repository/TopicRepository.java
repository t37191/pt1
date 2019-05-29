package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    //根据版块Id获取所有帖子
    public List<Topic> findBySectionId(Integer sectionId);
    public Topic findByTopicId(Integer topicId);
}
