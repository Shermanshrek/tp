package org.develop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class StatModel {
    @Id
    private String username;
    @Column(unique=true)
    private Long exercise_id;

    private double doTime;
    private LocalDateTime dateTime;
    private int errors;
}

