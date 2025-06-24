package com.zack.booking.service;

import com.zack.booking.dto.UserVerifyDto;
import com.zack.booking.exception.UserAlreadyExistsException;
import com.zack.booking.model.User;
import com.zack.booking.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public UserService(UserRepo userRepo, PasswordEncoder encoder, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }


    public User register(User user) throws UserAlreadyExistsException {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use");
        }
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already in use");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(UserVerifyDto dto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(dto.getUsername());
        } else {
            return "Failed";
        }
    }
}
