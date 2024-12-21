package org.develop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DifficultyLevelDTO {
    private String name;
    private int min_len;
    private int max_len;
    private double toggle_time;
    private double max_errors;
    private List<Boolean> zones;
}
