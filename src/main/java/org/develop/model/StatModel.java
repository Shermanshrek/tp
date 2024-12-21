package org.develop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class StatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exerciseDate;
    private String username;
    private int errorCount;
    private String exerciseName;
    private long durationInSeconds;
    private double meanTime;
    @Column(unique = true)
    private Long exerciseId;
}

