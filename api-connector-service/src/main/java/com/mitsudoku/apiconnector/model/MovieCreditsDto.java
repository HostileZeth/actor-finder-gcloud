package com.mitsudoku.apiconnector.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MovieCreditsDto {

    public int id;
    public ArrayList<Cast> cast;
    public ArrayList<Crew> crew;

    @Data
    @NoArgsConstructor
    public static class Cast{
        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public double popularity;
        public String profile_path;
        public int cast_id;
        public String character;
        public String credit_id;
        public int order;
    }

    @Data
    @NoArgsConstructor
    public static class Crew{
        public boolean adult;
        public int gender;
        public int id;
        public String known_for_department;
        public String name;
        public String original_name;
        public double popularity;
        public String profile_path;
        public String credit_id;
        public String department;
        public String job;
    }

}
