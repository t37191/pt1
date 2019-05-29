package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import com.google.common.collect.Lists;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;
    //根据标题和摘要查找论文
    public List<Outcome> getByTitleOrAbstr(String word){
//        List<Outcome> list = outcomeRepository.findAllByAbstrContaining(word);
        List<Outcome> list2 = outcomeRepository.findAllByTitleContaining(word);
        Set<String> set = new HashSet<>();
//        for(Outcome outcome:list){
//            set.add(outcome.getId());
//        }
        for(Outcome outcome:list2){
            set.add(outcome.getId());
        }
        List<Outcome> output = new ArrayList<>();
        for(Object id : set.toArray()){
            output.add(outcomeRepository.findById(id.toString()).get());
        }
        return output;
    }
    //根据关键字查找论文
    public List<Outcome> getByKeyWords(String keywords){
        return outcomeRepository.findAllByKeywords(keywords);
    }
    //根据作者查找论文
    public List<Outcome> getByAuthors(String author){
        QueryBuilder authorsQuery = QueryBuilders.nestedQuery("authors",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("authors.name", author)),
                ScoreMode.Total);

        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(authorsQuery);

        Iterable<Outcome> output = outcomeRepository.search(queryBuilder);
        List<Outcome> list = Lists.newArrayList(output);
        return list;
    }

    //获取单片论文信息
    public Outcome getOutcome(String id){
        return outcomeRepository.findById(id).get();
    }

    //增加论文
    public Outcome addOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //修改论文
    public Outcome updateOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //删除论文
    public void deleteOutcome(String id){
        outcomeRepository.deleteById(id);
    }
    /*
    //搜索
    public List<Outcome> serarchOutcome(String words){
        List<Outcome> l1 = outcomeRepository.findAllByTitleContaining(words);
        l1.addAll(outcomeRepository.findAllByAbstrContaining(words));
        Set<Integer> ids=new TreeSet<>();
        for(Outcome outcome :l1){
            ids.add(outcome.getOutcome_id());
        }
        List<Integer> id=new ArrayList<>(ids);
        Collections.reverse(id);
        l1.clear();
        for(Integer i :id){
            l1.add(outcomeRepository.findById(i).get());
        }
        return l1;
    }

    //搜索关键字
    public List<Outcome> seearchOutcomeByKeyword(String keyword){
        String[] sets=keyword.split(" ");
        List<Outcome> outcomes=new ArrayList<>();
        Set<Integer> ids=new TreeSet<>();
        for(String key :sets){
            List<Keyword> keys=keywordRepository.findByKeyword(key);
            for(Keyword k:keys){
                ids.add(k.getOutcome_id());
            }
        }
        List<Integer> tst=new ArrayList<>(ids);
        Collections.reverse(tst);
        for(Integer i:tst){
            outcomes.add(outcomeRepository.findById(i).get());
        }
        return outcomes;
    }

    //查询所有
    public List<Outcome> getAll(){
        return outcomeRepository.findAll();
    }
    public Keyword addKeyword(Keyword keyword){return keywordRepository.save(keyword);}*/
}
