package com.mitsudoku.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
    private int page;
    private int totalPages;
    private int totalResults;

    private List<T> results;
}
