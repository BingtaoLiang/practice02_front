package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ScoreController {
  @Autowired private RestTemplate restTemplate;

  @Value("${service.provider.url}")
  private String url;

  //根据文理科及成绩计算位次
  @RequestMapping("/rankSearch")
  public ModelAndView rankSearch(
      @RequestParam("sort") Integer sort,
      @RequestParam("score") Integer score) {
    ModelAndView mv = new ModelAndView("weici");
    mv.addObject("sort", sort);
    mv.addObject("score", score);
    Map map = restTemplate.getForObject(url+"/rankSearch?sort=" + sort + "&score=" + score, Map.class);
    Object rankLow = map.get("rankLow");
    Object rankHigh = map.get("rankHigh");
    mv.addObject("rankLow", rankLow);
    mv.addObject("rankHigh", rankHigh);
    return mv;
  }

  @RequestMapping("/scorelines")
  public ModelAndView schoolScoreLine(@RequestParam("schoolName") String schoolName) {
    ModelAndView mv = new ModelAndView("fenshuxian");
    Map map = restTemplate.getForObject(url+"/scorelines?schoolName=" + schoolName, Map.class);
    Object scorelines = map.get("scorelines");
    Object proAndCalData = map.get("proAndCalData");
    mv.addObject("scorelines",scorelines);  //通过服务端model的key获取院校分数线
    mv.addObject("proAndCalData",proAndCalData);  //通过服务端model的key获取专业招生计划和历年数据
    return mv;
  }
}
