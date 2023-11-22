package com.mitsudoku.readmodel.util;

import lombok.experimental.UtilityClass;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("ImageUrlUtil")
public class ImageUrlUtil {
    // TODO receive from config endpoint
    private static final String baseUrl = "http://image.tmdb.org/t/p/";
    private static final String size = "w500"; // TODO move to config maybe

    @Named("processUrl")
    public String processUrl(String fileName) {
        return String.format("%s%s%s", baseUrl, size, fileName);
    }
}
