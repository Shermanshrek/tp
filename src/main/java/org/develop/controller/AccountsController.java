package org.develop.controller;

import org.develop.model.UserModel;
import org.develop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {
    private final UserService userService;

    @Autowired
    AccountsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<List<UserModel>> getAccounts() {
        try{
            userService.getAllUsers();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/delete-user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
