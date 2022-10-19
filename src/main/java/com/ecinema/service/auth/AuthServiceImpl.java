package com.ecinema.service.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecinema.domain.User;
import com.ecinema.dto.req.LoginDto;
import com.ecinema.dto.req.RegisterDto;
import com.ecinema.dto.res.AuthResponseDto;
import com.ecinema.exception.CustomException;
import com.ecinema.exception.UserNotFoundException;
import com.ecinema.repo.RoleRepo;
import com.ecinema.repo.UserRepo;
import com.ecinema.security.AppUser;
import com.ecinema.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public AuthResponseDto login(LoginDto dto, String issuer) {
        return authenticate(dto.getEmail(), dto.getPassword(), issuer);
    }

    @Override
    public AuthResponseDto register(RegisterDto registerDto, String issuer) throws Exception {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new CustomException(HttpStatus.CONFLICT, "User with such email already exists");
        }

        User user = registerDto.toUser(passwordEncoder);
        user.setRoles(Set.of(roleRepo.findById(2).orElseThrow()));
        userRepo.save(user);
        return authenticate(registerDto.getEmail(), registerDto.getPassword(), issuer);
    }

    private AuthResponseDto authenticate(String email, String password, String issuer) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtTokenProvider.generateAccessToken(authentication, issuer);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
        User user = userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new AuthResponseDto(accessToken, refreshToken,
                new AuthResponseDto.User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBalance()));

    }

    @Override
    public AuthResponseDto refreshAccessToken(String refreshToken, String issuer) {
        DecodedJWT decodedJWT = jwtTokenProvider.verifyAndDecode(refreshToken);
        String email = decodedJWT.getSubject();
        User currentUser = userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
        UserDetails userDetails = AppUser.from(currentUser);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, null, userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String accessToken = jwtTokenProvider.generateAccessToken(userDetails, issuer);
        return new AuthResponseDto(
                accessToken,
                null,
                new AuthResponseDto.User(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), currentUser.getBalance())
        );
    }
}
