package org.develop.repository;

import org.develop.model.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepo extends JpaRepository<ExerciseModel, Long> {

}
