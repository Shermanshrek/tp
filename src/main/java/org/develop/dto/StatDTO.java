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
    private long exerciseId;
    private int errors;
    private String date;
    private long doTime;
}
