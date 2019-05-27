package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;
    //根据标题和摘要查找论文
    public List<Outcome> getByTitleAndAbstr(String title, String abstr){
        return outcomeRepository.findAllByTitleAndAbstr(title,abstr);
    }
    //根据关键字查找论文
    public List<Outcome> getByKeyWords(String keywords){
        return outcomeRepository.findAllByKeywords(keywords);
    }
//    //根据作者查找论文
//    public List<Outcome> getByAuthors(String author){
//        return outcomeRepository.findAllByAuthorContaining(author);
//    }

    //增加论文
    public Outcome addOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

    //修改论文
    public Outcome updateOutcome(Outcome outcome){
        return outcomeRepository.save(outcome);
    }

//    //删除论文
//    public void deleteOutcome(String id){
//        outcomeRepository.deleteById(id);
//    }
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
