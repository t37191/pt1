package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Service.OutcomeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "http://localhost:8000")
public class IndexController {
    @Autowired
    private OutcomeService outcomeService;
    /*
    查询服务
     */
    @GetMapping("/search/keyword")
    public List<Outcome> getOutcomeByKeywords(@RequestParam("keywords")String keywords) throws IOException {
        return outcomeService.getByKeyWords(keywords);
    }

    @GetMapping("/getByAuthors")
    public List<Outcome> getOutcomeByAuthor(@RequestParam("authors")String authors) throws IOException {
        return outcomeService.getByAuthor(authors);
    }

    @GetMapping("/getByTitleOrAbstr")
    public List<Outcome> getOutcomeByTitOrAbstr(@RequestParam("word")String word) throws IOException, JSONException {
        return outcomeService.getByTitle(word);
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
