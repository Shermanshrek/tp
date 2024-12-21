package org.develop.controller;

import org.develop.dto.DifficultyLevelDTO;
import org.develop.exceptions.DifficultyLevelAlreadyExistsException;
import org.develop.model.DifficultyLevelModel;
import org.develop.services.DifficultyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DifficultyLevelController {
    private final DifficultyLevelService difficultyLevelService;

    @Autowired
    public DifficultyLevelController(DifficultyLevelService diffLevelService) {
        this.difficultyLevelService = diffLevelService;
        difficultyLevelService.keyboardAreasInit();
    }

    @PostMapping("/admin/create-difficult")
    public ResponseEntity createDifficultyLevel(@RequestBody DifficultyLevelDTO entity) {
        try {
            difficultyLevelService.createDifficultyLevel(entity);
            return ResponseEntity.ok().body(entity);
        } catch (DifficultyLevelAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/admin/get-difficulty-levels")
    public List<DifficultyLevelModel> getAllDifficultyLevelsAdmin() {
        return difficultyLevelService.getAllDifficultyLevels();
    }
    @GetMapping("/user/get-difficulty-levels")
    public List<DifficultyLevelModel> getUserDifficultyLevelsUser() {
        return difficultyLevelService.getAllDifficultyLevels();
    }
}
