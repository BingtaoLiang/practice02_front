package com.practice.controller;

import com.practice.dto.PaginationDTO;
import com.practice.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SchoolController {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${service.provider.url}")
  private String url;

  @GetMapping("/")
  @ResponseBody
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView("index");
    mv.addObject("regions", restTemplate.getForObject(url+"/area", List.class));
    mv.addObject("majors", restTemplate.getForObject(url+"/majorList", List.class));
    return mv;
  }

  //查询某一地区的所有学校，并加入分页功能
  @RequestMapping("/schoolList")
  public ModelAndView getSchools(@RequestParam("reid") Integer reid,
                                 @RequestParam(value = "page",defaultValue = "1") Integer page) {
    ModelAndView mv = new ModelAndView("school");
    mv.addObject("reid",reid);
    mv.addObject("schools",restTemplate.getForObject(url+"/area2?reid="+reid+"&page="+page, PaginationDTO.class));
    return mv;
  }

  /*
   * 根据学校id查询学校具体信息，如学校简介，学校层次等
   * */
  @RequestMapping("/school_desc_l/{scid}")
  public ModelAndView getSchoolInfo(@PathVariable("scid") Integer scid){
    ModelAndView mv = new ModelAndView("school_desc");
    mv.addObject("school",restTemplate.getForObject(url+"/school_desc_l/"+scid, School.class));
    return mv;
  }

  /*
   * 输入学校名称查询相应的学校
   * */
  @RequestMapping("/schoolsearch")
  public ModelAndView getSchoolByName(HttpServletRequest request){
    String schoolname = request.getParameter("schoolname");
    ModelAndView mv = new ModelAndView("school");
    mv.addObject("schools",restTemplate.getForObject(url+"/schoolsearch?schoolname="+schoolname, PaginationDTO.class));
    return mv;
  }

  //首页上的“找大学/查专业”
  @RequestMapping("/indexSearch")
  public String indexSearch(HttpServletRequest request, Model model){
    String indexSearchInput = request.getParameter("indexSearhInput");
    model.addAttribute("searchInput",indexSearchInput);
    return "indexSearch";
  }

  //首页上“找大学/查专业”--->找大学
  @RequestMapping("/indexSearch/school")
  public ModelAndView getSchool(HttpServletRequest request){
    String indexSearchInput = request.getParameter("indexSearchInput");
    ModelAndView mv = new ModelAndView("indexSearch");
    mv.addObject("schools",restTemplate.getForObject(url+"/indexSearch/school?indexSearchInput="+indexSearchInput, List.class));
    return mv;
  }


}
