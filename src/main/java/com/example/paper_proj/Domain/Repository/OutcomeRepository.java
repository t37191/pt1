package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Outcome;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OutcomeRepository extends ElasticsearchRepository<Outcome,String> {
    /*
    查找
     */

    List<Outcome> findAllByTitleOrAbstr(String word);

    List<Outcome> findAllByKeywords(String keywords);
    List<Outcome> findAllByAuthorsContaining(String author);
    /*
    删除
     */
    public void deleteById(String id);
}
