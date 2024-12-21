package org.develop.controller;

import org.develop.model.UserModel;
import org.develop.services.JwtService;
import org.develop.services.UserService;
import org.develop.util.DeleteBearer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    AccountsController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/admin/get-all-accounts")
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
            if (token.startsWith(DeleteBearer.bearer)) {
                token = DeleteBearer.deleteBearer(token);
            } else return ResponseEntity.badRequest().build();
            String role = jwtService.extractRole(token);
            if (role.equals("ROLE_ADMIN")) {
                userService.deleteUser(id);
                return ResponseEntity.ok().build();
            } else return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
