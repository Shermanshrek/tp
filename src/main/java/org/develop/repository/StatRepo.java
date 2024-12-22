package org.develop.repository;

import org.develop.model.StatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatRepo extends JpaRepository<StatModel, Long>  {
    List<StatModel> findByUsername(String username);
    Optional<StatModel> findByExerciseId(Long exerciseId);
    Optional<StatModel> findByUsernameAndExerciseId(String username, Long exerciseId);
}
