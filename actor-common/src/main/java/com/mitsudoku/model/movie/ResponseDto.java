package com.mitsudoku.model.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto<T> {

    public int page;
    public int total_pages;
    public int total_results;

    public List<T> results;
}
