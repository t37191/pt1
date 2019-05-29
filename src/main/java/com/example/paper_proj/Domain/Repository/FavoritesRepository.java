package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites,Integer> {
    public List<Favorites> findByUserId(int userId);
    public Favorites findByUserIdAndTopicId(int userId, int topicId);
}
