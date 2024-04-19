package com.ocprojettrois.locationprojettree.Services.Auth;

import com.ocprojettrois.locationprojettree.Models.Auth.AuthenticationResponse;
import com.ocprojettrois.locationprojettree.Models.Role.Role;
import com.ocprojettrois.locationprojettree.Models.Token.Token;
import com.ocprojettrois.locationprojettree.Models.User.User;
import com.ocprojettrois.locationprojettree.Models.User.UserDto.LoginRequest;
import com.ocprojettrois.locationprojettree.Models.User.UserDto.RegisterRequest;
import com.ocprojettrois.locationprojettree.Repository.Token.TokenRepository;
import com.ocprojettrois.locationprojettree.Repository.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        // check if user already exist. if exist than authenticate the user
        if(repository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthenticationResponse(null);
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        Date date = new Date();
        user.setCreated_at(date);
        user.setUpdated_at(date);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);

        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt);

    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt);

    }
    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public List<User> loadAllUsers() {
        return repository.findAll();
    }
}

