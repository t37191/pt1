package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.EmbeddedKey.KeywordPK;
import com.example.paper_proj.Domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, KeywordPK> {

    public List<Keyword> findByKeywordPKKeyword(String keyword);

}
