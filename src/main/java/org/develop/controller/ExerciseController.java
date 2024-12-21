package org.develop.controller;

import org.develop.dto.ExerciseDTO;
import org.develop.exceptions.ExerciseNotFoundException;
import org.develop.model.ExerciseModel;
import org.develop.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    private final ExerciseService service;

    @Autowired
    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/do-exercise/{id}")
    public ExerciseModel getExercise(@PathVariable Long id) throws Exception {
        return service.getExerciseById(id);
    }

    @PostMapping("/admin/create-exercise")
    public ResponseEntity createExercise(@RequestBody ExerciseDTO entity) {
        try {
            service.createExercise(entity);
            return ResponseEntity.ok().body(entity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/admin/exercises/{id}")
    public ResponseEntity deleteExercise(@PathVariable Long id) {
        try {
            service.deleteExercise(id);
            return ResponseEntity.ok().body(id);
        } catch (ExerciseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/admin/get-exercises")
    public List<ExerciseModel> getExercisesAdmin() {
        return service.getAllExercises();
    }

    @GetMapping("/user/get-exercises")
    public List<ExerciseModel> getExercisesUser() {
        return service.getAllExercises();
    }
}
