package com.practice.model;

import lombok.Data;

/**
 * (Schoolscore)实体类
 *
 * @author makejava
 * @since 2020-03-17 09:19:43
 */
@Data
public class Schoolscore {
    
    private Integer scid;
    
    private Integer sort;
    
    private String name;
    
    private Integer minScore;
    
    private Integer minRank;
    
    private Integer batch;
    
    private Integer year;

}