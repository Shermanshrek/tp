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
    @GetMapping("/admin/get-stat-user/{username}")
    public List<ExerciseModel> getStatUser(@PathVariable String username) {
        return statService.getStats(username);
    }

    @GetMapping("/admin/get-all-users")
    public List<UserModel> getStatAll() {
        return userService.getAllUsers();
    }
    @GetMapping("/admin/get-all-stat")
    public ResponseEntity<List<StatModel>> getAllStat(){
        try{
         return ResponseEntity.ok().body(statService.getAllStat());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/admin/get-exercise-stat/{username}/{id}")
    public ResponseEntity<List<StatModel>> getExerciseStatForAdmin(@PathVariable Long id, @PathVariable String username) {
        try {
            return ResponseEntity.ok().body(statService.getStatForExercise(username, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping("/user/get-exercise-stat/{username}/{id}")
    public ResponseEntity<List<StatModel>> getExerciseStat(@PathVariable Long id, @PathVariable String username) {
        try {
            return ResponseEntity.ok().body(statService.getStatForExercise(username, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/user/get-exercise-stat/{username}")
    public ResponseEntity<List<StatModel>> getExerciseStatByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok().body(statService.getStatByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
