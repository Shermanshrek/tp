package org.develop.controller;

import org.develop.dto.StatDTO;
import org.develop.model.ExerciseModel;
import org.develop.model.StatModel;
import org.develop.model.UserModel;
import org.develop.services.StatService;
import org.develop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatController {
    private final StatService statService;
    private final UserService userService;

    @Autowired
    public StatController(StatService statService, UserService userService) {
        this.statService = statService;
        this.userService = userService;
    }

    //get user stat
    //id = user_id
    @GetMapping("/get-stat-user/{username}")
    public List<ExerciseModel> getStatUser(@PathVariable String username) {
        return statService.getStats(username);
    }

    @GetMapping("/admin/get-all-users")
    public List<UserModel> getStatAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/save-exercise-stat")
    public ResponseEntity<String> createExerciseStat(@RequestBody StatDTO statDTO) {
        try {
            statService.saveStat(statDTO);
            return ResponseEntity.ok().body("Exercise stat created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save stat");
        }
    }

    @GetMapping("/user/get-exercise-stat/{id}")
    public ResponseEntity<StatModel> getExerciseStat(@PathVariable Long id) {
        //id = id упражнения
        try {
            return ResponseEntity.ok().body(statService.getStatForExercise(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
