package org.develop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private String exerciseText;
    private int autoLength;
    private String difficultyLevelName;
    private String exerciseName;
    private boolean isAuto;
}
