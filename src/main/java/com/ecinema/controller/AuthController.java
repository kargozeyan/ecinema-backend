package com.ecinema.controller;

import com.ecinema.dto.req.LoginDto;
import com.ecinema.dto.req.RegisterDto;
import com.ecinema.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto, HttpServletRequest req) {
        return ResponseEntity
                .ok(authService.login(dto, req.getRequestURL().toString()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto dto, HttpServletRequest req) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(dto, req.getRequestURL().toString()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken, HttpServletRequest req) {
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken, req.getRequestURL().toString()));
    }
}
