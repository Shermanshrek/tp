package org.develop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.develop.util.KeyboardArea;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class DifficultyLevelModel {
    @Id
    private String name;
    private int min_len;
    private int max_len;
    private double toggle_time;
    private double max_errors;
    @ElementCollection
    private List<KeyboardArea> zones;

    @OneToMany(mappedBy = "difficultyLevelName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseModel> exercises;
}

