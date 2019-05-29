package com.example.paper_proj.Domain.Repository;

import com.example.paper_proj.Domain.bbsSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface bbsSectionRepository extends JpaRepository<bbsSection,Integer> {
    //查询
    public bbsSection findByName(String section_name);
    public bbsSection findBySectionid(Integer section_id);
    public List<bbsSection> findByMasterid(Integer master_id);

    //获取所有版块
    List<bbsSection> findBySectionidNotNull();

    //删除
    @Transactional
    @Modifying
    public void deleteBySectionid(Integer section_id);

    @Transactional
    @Modifying
    public void deleteByMasterid(Integer master_id);
}
