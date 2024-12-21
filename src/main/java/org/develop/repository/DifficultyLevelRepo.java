package org.develop.repository;

import org.develop.model.DifficultyLevelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DifficultyLevelRepo extends JpaRepository<DifficultyLevelModel, String> {
    Optional<DifficultyLevelModel> findByName(String name);
}
