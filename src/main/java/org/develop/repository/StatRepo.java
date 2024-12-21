package org.develop.repository;

import org.develop.model.StatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatRepo extends JpaRepository<StatModel, Long>  {
    List<StatModel> findByUsername(String username);
}
