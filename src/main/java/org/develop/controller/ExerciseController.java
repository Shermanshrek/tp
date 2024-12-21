package org.develop.controller;

import org.develop.dto.ExerciseDTO;
import org.develop.exceptions.ExerciseNotFoundException;
import org.develop.model.ExerciseModel;
import org.develop.services.ExerciseService;
import org.develop.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ExerciseController {

    private final ExerciseService service;
    private final JwtService jwtService;

    @Autowired
    public ExerciseController(ExerciseService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @GetMapping("/user/do-exercise/{id}")
    public ExerciseModel getExercise(@PathVariable Long id) throws Exception {
        return service.getExerciseById(id);
    }

    @PostMapping("/admin/create-exercise")
    public ResponseEntity createExercise(@RequestBody ExerciseDTO entity, @RequestHeader("Authorization") String token) {
        try {
            // Убираем префикс "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7); // Удаляем первые 7 символов ("Bearer ")
            } else {
                return ResponseEntity.badRequest().body("Invalid token format");
            }
            // Извлечение роли
            String role = jwtService.extractRole(token);
            if (role.equals("ROLE_ADMIN")) {
                service.createExercise(entity);
                return ResponseEntity.ok().body(entity);
            } else {
                return ResponseEntity.badRequest().body("You are not an admin");
            }
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
