package com.ecinema.dto.req;

import lombok.Data;
import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String password;
}
