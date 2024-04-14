package com.ocprojettrois.locationprojettree.Controllers;

import com.ocprojettrois.locationprojettree.Models.Auth.AuthenticationResponse;
import com.ocprojettrois.locationprojettree.Models.User.User;
import com.ocprojettrois.locationprojettree.Models.User.UserDto.LoginRequest;
import com.ocprojettrois.locationprojettree.Models.User.UserDto.RegisterRequest;
import com.ocprojettrois.locationprojettree.Services.Auth.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/auth")
@Tag(name = "auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ResponseEntity<List<User>> me(){
        return ResponseEntity.ok(authService.loadAllUsers());
    }
}