package org.develop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatDTO {
    private String username;
    private long exerciseId;
    private int errors;
    private String date;
    private double meanTime;
    private long doTime;
    private String exerciseName;
}
