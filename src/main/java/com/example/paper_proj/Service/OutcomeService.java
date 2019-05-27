package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Keyword;
import com.example.paper_proj.Domain.Repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeService {
//    @Autowired
//    private PaperRepository paperRepository;
//
//    @Autowired
//    private PatentRepository patentRepository;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private OutcomeRepository outcomeRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    //搜索
//    public List<Outcome> serarchOutcome(String words){
//        List<Outcome> l1 = outcomeRepository.findAllByTitleContaining(words);
//        l1.addAll(outcomeRepository.findAllByAbstrContaining(words));
//        Set<Integer> ids=new TreeSet<>();
//        for(Outcome outcome :l1){
//            ids.add(outcome.getOutcome_id());
//        }
//        List<Integer> id=new ArrayList<>(ids);
//        Collections.reverse(id);
//        l1.clear();
//        for(Integer i :id){
//            l1.add(outcomeRepository.findById(i).get());
//        }
//        return l1;
//    }

    //搜索关键字
//    public List<Outcome> seearchOutcomeByKeyword(String keyword){
//        String[] sets=keyword.split(" ");
//        List<Outcome> outcomes=new ArrayList<>();
//        Set<Integer> ids=new TreeSet<>();
//        for(String key :sets){
//            List<Keyword> keys=keywordRepository.findByKeyword(key);
//            for(Keyword k:keys){
//                ids.add(k.getOutcome_id());
//            }
//        }
//        List<Integer> tst=new ArrayList<>(ids);
//        Collections.reverse(tst);
//        for(Integer i:tst){
//            outcomes.add(outcomeRepository.findById(i).get());
//        }
//        return outcomes;
//    }
//
//    //查询所有
//    public List<Outcome> getAll(){
//        return outcomeRepository.findAll();
//    }
//
//    //test
//    public Paper addPaper(Paper paper){
//        return paperRepository.save(paper);
//    }

    public Keyword addKeyword(Keyword keyword){return keywordRepository.save(keyword);}
}
