package org.develop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.develop.dto.JwtAuthenticationResponse;
import org.develop.dto.SignInRequest;
import org.develop.dto.SignUpRequest;
import org.develop.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService authenticationService;
    @Operation(summary = "Register")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp (@RequestBody @Valid SignUpRequest request){
        return authenticationService.signUp(request);
    }

    @Operation
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody /*@Valid*/ SignInRequest request) {
        System.out.println(request);
        return authenticationService.signIn(request);
    }
}

