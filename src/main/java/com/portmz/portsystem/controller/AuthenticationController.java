package com.portmz.portsystem.controller;

import com.portmz.portsystem.model.user.*;
import com.portmz.portsystem.repository.UserRepository;
import com.portmz.portsystem.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

        @Autowired
        private TokenService tokenService;
    
        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
           // var token = tokenService.generateToken((User) auth.getPrincipal());
            var accessToken = tokenService.generateToken((User) auth.getPrincipal());

            // Gere um refreshToken aleatório e associe ao usuário (neste exemplo, usei uma classe UserService fictícia)
            String refreshToken = tokenService.generateRefreshToken();
            userService.saveRefreshToken(auth.getName(), refreshToken);
            return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));
        }

        @PostMapping("/register")
        public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
            if(this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.username(), encryptedPassword, data.role(), data.firstName(), data.lastName(), data.dateBirth(), data.gender(), data.cell(), data.email());
            this.repository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }

