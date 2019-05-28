package com.example.paper_proj.Controller;


import com.example.paper_proj.Domain.bbsSection;
import com.example.paper_proj.Service.bbsSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bbs/section")
@CrossOrigin(origins = "http://localhost:8000")
public class BBS_SectionController {
    @Autowired
    private bbsSectionService sectionService;

    /*
    查询
     */
    @GetMapping(value="/sectionlist")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public List<bbsSection> getAll(){return sectionService.getAllSection();}

    @GetMapping(value="/get")
    public bbsSection getSectionByName(@RequestParam("section.name") String section_name){
        return sectionService.getByName(section_name);
    }
    @GetMapping(value="/getBySectionId")
    public bbsSection getSectionBySectionId(@RequestParam("section.id") Integer section_id){
        return sectionService.getBySectionId(section_id);
    }
    @GetMapping(value = "/getByMasterId")
    public List<bbsSection> getSectionByMasterId(@RequestParam("master.id") Integer master_id){
        return sectionService.getByMasterId(master_id);
    }
    /*
    增加
     */
    @PostMapping(value="/add")
    @PreAuthorize("hasAuthority('admin')")
    public bbsSection addSection(@RequestBody bbsSection bbsSection){
        return sectionService.addSection(bbsSection);
    }
    /*
    删除
     */
    @DeleteMapping(value ="/deleteBySectionId")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteSectionBySectionId(@RequestParam("section.id") Integer section_id) {
        sectionService.deleteBySectionId(section_id);
    }
    @DeleteMapping(value="/deleteByMasterId")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteSectionByMasterId(@RequestParam("master.id") Integer master_id){
        sectionService.deleteByMasterId(master_id);
    }

    /*
        改
         */
    @PutMapping(value="/update")
    @PreAuthorize("hasAuthority('admin')")
    public bbsSection updateSection(@RequestBody bbsSection bbsSection){
        return sectionService.updateSection(bbsSection);
    }
}
