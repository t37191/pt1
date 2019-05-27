package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class IndexController {
    @Autowired
    private OutcomeService outcomeService;
    /*
    查询服务
     */
    @GetMapping("/getByKeywords")
    public List<Outcome> getOutcomeByKeywords(@RequestParam("keywords")String keywords){
        return outcomeService.getByKeyWords(keywords);
    }

//    @GetMapping("/getByAuthors")
//    public List<Outcome> getOutcomeByAuthor(@RequestParam("authors")String authors){
//        return outcomeService.getByAuthors(authors);
//    }

    @GetMapping("/getByTitleAndAbstr")
    public List<Outcome> getOutcomeByTitAndAbstr(@RequestParam("title")String title,@RequestParam("abstract")String abstr){
        return outcomeService.getByTitleAndAbstr(title,abstr);
    }

    /*
    添加成果
     */
    @PostMapping(value="/add")
    public Outcome addOutcome(@RequestBody Outcome outcome){
        return outcomeService.addOutcome(outcome);
    }
/*
    @GetMapping("/paper/{key}")
    public List<Outcome> getPapers(@PathVariable("key") String title){
        return outcomeService.serarchOutcome(title);
    }

    @GetMapping("/paper?key={key}")
    public List<Outcome> getPapers2(@RequestParam("key") String title){
        return outcomeService.serarchOutcome(title);
    }

    @PostMapping("/keyword")
    public Keyword addKeyword(@RequestBody Keyword keyword){ return outcomeService.addKeyword(keyword);}

    @GetMapping("/outcome")
    public List<Outcome> getAllOutcome(){
        return outcomeService.getAll();
    }

    @GetMapping("/outcome/{keyword}")
    public List<Outcome> getOutcomes(@PathVariable("keyword")String keyword){
        return outcomeService.seearchOutcomeByKeyword(keyword);
    }*/
}
