package org.develop.services;

import org.develop.model.Role;
import org.develop.model.UserModel;
import org.develop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //сохранение пользователя
    public UserModel save(UserModel user){
        return userRepository.save(user);
    }
    //создание пользователя
    public UserModel create(UserModel user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Такой пользователь уже существует");
        }
        if (userRepository.existsByEmail((user.getEmail()))){
            throw new RuntimeException("Пользователь с такие email уже существует");
        }
        return save(user);
    }
    //получение пользователя по имени
    public UserModel getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Пользователь не найден"));
    }
    //получение пользователя по имени пользователя для SpringSecurity
    public UserDetailsService userDetailsService(){
        return  this::getByUsername;
    }
    //получение текущего пользователя
    public UserModel getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }
    //дать права администратора текущему пользователю
    @Deprecated
    public void getAdmin(){
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}

