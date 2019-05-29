package com.example.paper_proj.Service;


import com.example.paper_proj.Domain.Repository.bbsSectionRepository;
import com.example.paper_proj.Domain.bbsSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bbsSectionService {
    @Autowired
    private bbsSectionRepository bbs_sectionRepository;

    //查询
    public List<bbsSection> getAllSection(){
        return bbs_sectionRepository.findAll();
    }

    //通过板块名查找板块
    public bbsSection getByName(String section_name){
        return bbs_sectionRepository.findByName(section_name);
    }
    //通过板块id查找板块
    public bbsSection getBySectionId(Integer section_id){
        return  bbs_sectionRepository.findBySectionid(section_id);
    }
    //通过版主id获取板块集合
    public List<bbsSection> getByMasterId(Integer master_id){
        return bbs_sectionRepository.findByMasterid(master_id);
    }

    //增加板块
    public bbsSection addSection(bbsSection section){
        return bbs_sectionRepository.save(section);
    }
    //修改板块
    public bbsSection updateSection(bbsSection section){
        return bbs_sectionRepository.save(section);
    }

    //删除板块
    public void deleteBySectionId(Integer section_id){
        bbs_sectionRepository.deleteBySectionid(section_id);
    }
    public void deleteByMasterId(Integer master_id){
        bbs_sectionRepository.deleteByMasterid(master_id);
    }




}
