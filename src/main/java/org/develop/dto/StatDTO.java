package org.develop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatDTO {
    private String username;
    private Long exercise_id;
    private int errors;
    private LocalDateTime date;
    private double doTime;
}
