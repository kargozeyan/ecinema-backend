package com.ecinema.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class EditRequestDto {
    @NotEmpty
    @Size(min = 2, message = "First name must have at least 2 characters")
    private final String firstName;

    @NotEmpty
    @Size(min = 2, message = "Last name must have at least 2 characters")
    private final String lastName;

    @NotEmpty
    @Email
    private final String email;

    @NotEmpty
    @Size(min = 8, message = "Password must have at least 8 charactes")
    private final String oldPassword;

    @NotEmpty
    @Size(min = 8, message = "Password must have at least 8 charactes")
    private final String newPassword;
}
