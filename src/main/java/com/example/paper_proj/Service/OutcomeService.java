package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Keyword;
import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Paper;
import com.example.paper_proj.Domain.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OutcomeService {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PatentRepository patentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private OutcomeRepository outcomeRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    //搜索标题
    public List<Outcome> serarchOutcome(String title){
        return outcomeRepository.findByTitleContaining(title);
    }

    //搜索关键字
    public List<Outcome> seearchOutcomeByKeyword(String keyword){
        String[] sets=keyword.split(" ");
        List<Outcome> outcomes=new ArrayList<>();
        Set<Integer> ids=new TreeSet<>();
        for(String key :sets){
            List<Keyword> keys=keywordRepository.findByKeywordPKKeyword(key);
            for(Keyword k:keys){
                ids.add(k.getKeywordPK().getOutcome_id());
            }
        }
        List<Integer> tst=new ArrayList<>(ids);
        Collections.reverse(tst);
        for(Integer i:tst){
            outcomes.add(outcomeRepository.findById(i).get());
        }
        return outcomes;
    }

    //test
    public Paper addPaper(Paper paper){
        return paperRepository.save(paper);
    }
}
