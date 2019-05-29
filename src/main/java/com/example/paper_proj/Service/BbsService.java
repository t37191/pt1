package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Favorites;
import com.example.paper_proj.Domain.Repository.FavoritesRepository;
import com.example.paper_proj.Domain.Repository.TopicRepository;
import com.example.paper_proj.Domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BbsService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    //获取当前Section的所有topic
    public List<Topic> getAllTopicsBySectionId(Integer sctionId){
        return topicRepository.findBySectionId(sctionId);
    }

    //在当前Section发布topic
    public Topic createNewTopic(Topic topic){
        return topicRepository.save(topic);
    }

    //删除topic
    public void deleteTopic(int topicId){
        topicRepository.delete(topicRepository.findByTopicId(topicId));
    }

    /*//查看我点赞的topic
    public List<Favorites> findFavoritesByUserId(int userId){
        return favoritesRepository.findByUserId(userId);
    }*/
    //查看我点赞的topic
    public List<String> findFavoritesByUserId(int userId){
        List<Favorites> list=favoritesRepository.findByUserId(userId);
        List<String> topicNames=new ArrayList<String>();
        for (Favorites fav:list
             ) {
            topicNames.add(topicRepository.findByTopicId(fav.getTopicId()).getTopicName());
        }
        return topicNames;
    }

    //点赞topic
    public Favorites createNewFavorite(Favorites favorites){
        return favoritesRepository.save(favorites);
    }

    //取消点赞topic
    public void deleteFavorite(int userId,int topicId){
        favoritesRepository.delete(favoritesRepository.findByUserIdAndTopicId(userId,topicId));
    }


}
