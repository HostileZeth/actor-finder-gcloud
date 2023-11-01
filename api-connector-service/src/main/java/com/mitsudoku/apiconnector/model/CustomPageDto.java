package com.mitsudoku.apiconnector.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomPageDto {

    public int page;
    public int total_pages;
    public int total_results;

}
