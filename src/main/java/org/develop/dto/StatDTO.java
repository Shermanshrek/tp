package org.develop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatDTO {
    private String username;
    private long exerciseId;
    private int errors;
    private Date date;
    private double meanTime;
    private long doTime;
    private String exerciseName;
}
