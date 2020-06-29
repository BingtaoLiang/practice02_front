package com.practice.controller;

import com.practice.dto.PaginationDTO;
import com.practice.model.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProfessionController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.provider.url}")
    private String url;

    //根据学科类别查找对应的专业，如工科，农科，文科下的专业
    @RequestMapping("/professionList")
    public ModelAndView getProfessions(@RequestParam("major") String major,
                                       @RequestParam(value = "page",defaultValue = "1")Integer page){
        ModelAndView mv = new ModelAndView("profession");
        mv.addObject("major",major);
        mv.addObject("professions",restTemplate.getForObject(url+"/major?major="+major+"&page="+page, PaginationDTO.class));
        return mv;
    }


  // 根据专业名称查找专业介绍及开设该专业的院校
  @RequestMapping("/profession_desc/{proname}")
  public ModelAndView getProfessionInfo(@PathVariable("proname") String proname) {
    ModelAndView mv = new ModelAndView("profession_desc");
    mv.addObject("professions", restTemplate.getForObject(url+"/professionInfo/" + proname, Profession.class));
    return mv;
    }


    //首页“找大学/查专业”--->查专业
    @RequestMapping("indexSearch/profession")
    public ModelAndView getProfessions(HttpServletRequest request){
        String indexSearchInput = request.getParameter("indexSearchInput");
        ModelAndView mv = new ModelAndView("indexSearch");
        mv.addObject("professionDTO", restTemplate.getForObject(url+"/indexSearch/profession?indexSearchInput=" + indexSearchInput, PaginationDTO.class));
        return mv;
    }

    //根据专业名称查找专业
    @RequestMapping("/majors")
    public ModelAndView getprofessionByName(@RequestParam("profession") String profession){
        ModelAndView mv = new ModelAndView("profession");
        mv.addObject("professions", restTemplate.getForObject(url+"/majors?profession=" + profession, PaginationDTO.class));
        return mv;
    }

}
