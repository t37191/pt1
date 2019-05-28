package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Outcome;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OutcomeRepository extends ElasticsearchRepository<Outcome,String> {
    /*
    查找
     */

    List<Outcome> findAllByTitleContaining(String word);
    List<Outcome> findAllByAbstrContaining(String word);

    List<Outcome> findAllByKeywords(String keywords);
    /*
    删除
     */
//    public void deleteById(String id);
}
