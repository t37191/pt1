package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {

    public List<Outcome> findByTitleContaining(String titlelike);

    //public Outcome findByOutcome_id(Integer outcome_id);
}
