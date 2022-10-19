package com.ecinema.service.auth;

import com.ecinema.dto.req.LoginDto;
import com.ecinema.dto.req.RegisterDto;
import com.ecinema.dto.res.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(LoginDto dto, String issuer);

    AuthResponseDto register(RegisterDto registerDto, String issuer) throws Exception;

    AuthResponseDto refreshAccessToken(String refreshToken, String issuer);
}
