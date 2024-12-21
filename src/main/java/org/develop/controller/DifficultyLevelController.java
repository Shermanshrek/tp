package org.develop.controller;

import org.develop.dto.DifficultyLevelDTO;
import org.develop.exceptions.DifficultyLevelAlreadyExistsException;
import org.develop.model.DifficultyLevelModel;
import org.develop.services.DifficultyLevelService;
import org.develop.services.JwtService;
import org.develop.util.DeleteBearer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DifficultyLevelController {
    private final DifficultyLevelService difficultyLevelService;
    private final JwtService jwtService;

    @Autowired
    public DifficultyLevelController(DifficultyLevelService diffLevelService, JwtService jwtService) {
        this.difficultyLevelService = diffLevelService;
        difficultyLevelService.keyboardAreasInit();
        this.jwtService = jwtService;
    }

    @PostMapping("/admin/create-difficult")
    public ResponseEntity createDifficultyLevel(@RequestBody DifficultyLevelDTO entity, @RequestHeader("Authorization") String token) {
        System.out.println("token: " + token);
        try {
            if (token.startsWith(DeleteBearer.bearer)) {
                token = DeleteBearer.deleteBearer(token);
            } else {
                return ResponseEntity.badRequest().body("Invalid token format");
            }
            String role = jwtService.extractRole(token);
            if (role.equals("ROLE_ADMIN")) {
                difficultyLevelService.createDifficultyLevel(entity);
                return ResponseEntity.ok().body(entity);
            } else {
                return ResponseEntity.badRequest().body("You are not an admin");
            }
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
