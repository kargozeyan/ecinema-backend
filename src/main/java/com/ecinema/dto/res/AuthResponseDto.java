package com.ecinema.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthResponseDto {
    private final String accessToken;
    private final String refreshToken;
    private final User user;

        public record User(String firstName, String lastName, String email, double balance) {
    }
}
