package com.practice.model;

import lombok.Data;

/**
 * (ScoreRank)实体类
 *
 * @author makejava
 * @since 2020-03-30 16:55:07
 */
@Data
public class ScoreRank{
    
    private Integer id;
    /**
    * 分数
    */
    private Integer score;
    /**
    * 位次
    */
    private Integer rank;
    /**
    * 年份
    */
    private Integer year;
    /**
    * 文理科，1为理科，2为文科
    */
    private String sort;

}