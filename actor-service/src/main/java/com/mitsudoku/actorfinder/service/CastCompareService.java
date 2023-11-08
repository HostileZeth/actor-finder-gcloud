package com.mitsudoku.actorfinder.service;

import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.model.movie.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CastCompareService {

    public List<CastDto> getCastIntersection(MovieDto movieDto1, MovieDto movieDto2) {
        Set<Integer> firstSet = movieDto1.getCreditsDto().getCast().stream().map(CastDto::getId).collect(Collectors.toSet());
        Set<Integer> intersect = movieDto2.getCreditsDto().getCast().stream().map(CastDto::getId).filter(firstSet::contains).collect(Collectors.toSet());
        return movieDto1.getCreditsDto().getCast().stream().filter(i -> intersect.contains(i.getId())).collect(Collectors.toList());
    }

}
