package com.mitsudoku.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrewDto {
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
