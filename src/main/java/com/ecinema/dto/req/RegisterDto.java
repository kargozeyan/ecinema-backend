package com.ecinema.dto.req;

import com.ecinema.domain.Role;
import com.ecinema.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@AllArgsConstructor
public class RegisterDto {

    @NotEmpty
    @Size(min = 2, message = "First name must have at least 2 characters")
    private String firstName;

    @NotEmpty
    @Size(min = 2, message = "Last name must have at least 2 characters")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password must have at least 8 charactes")
    private String password;

    public User toUser(PasswordEncoder encoder) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setBalance(0d);

        return user;
    }
}
