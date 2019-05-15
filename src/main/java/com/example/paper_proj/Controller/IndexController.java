package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Outcome;
import com.example.paper_proj.Domain.Paper;
import com.example.paper_proj.Service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IndexController {
    @Autowired
    private OutcomeService outcomeService;

    @PostMapping("/paper")
    public Paper addPaper(@RequestBody Paper paper){
        return outcomeService.addPaper(paper);
    }

    @GetMapping("/paper/{key}")
    public List<Outcome> getPapers(@PathVariable("key") String title){
        return outcomeService.serarchOutcome(title);
    }

    @GetMapping("/outcome/{keyword}")
    public List<Outcome> getOutcomes(@PathVariable("keyword")String keyword){
        return outcomeService.seearchOutcomeByKeyword(keyword);
    }
}
