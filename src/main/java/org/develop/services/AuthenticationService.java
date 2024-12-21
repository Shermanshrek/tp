package org.develop.services;

import lombok.RequiredArgsConstructor;
import org.develop.dto.JwtAuthenticationResponse;
import org.develop.dto.SignInRequest;
import org.develop.dto.SignUpRequest;
import org.develop.model.Role;
import org.develop.model.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //регистрация пользователя
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest){
        var user = UserModel.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userService.create(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
    //аутентификация пользователя
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return new JwtAuthenticationResponse(jwt);
    }
}

