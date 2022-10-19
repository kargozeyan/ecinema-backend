package com.ecinema.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    //    @Value(value = "jwt-secret")
    private static final String secret = "some_secret";
    private static final long ACCESS_TOKEN_LIFE = 1000L * 60 * 10; // 10 minutes
    private static final long REFRESH_TOKEN_LIFE = 1000L * 60 * 60 * 24 * 30; // 30 days
    private static final Algorithm JWT_ALGORITHM = Algorithm.HMAC256(secret.getBytes());

    public String generateAccessToken(Authentication authentication, String issuer) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return generateAccessToken(userDetails, issuer);
    }

    public String generateAccessToken(UserDetails userDetails, String issuer) {
        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFE);

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("ROLES", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(expirationDate)
                .withIssuer(issuer)
                .sign(JWT_ALGORITHM);

    }

    public String generateRefreshToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return generateRefreshToken(userDetails);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Date expirationDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_LIFE);

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(expirationDate)
                .sign(JWT_ALGORITHM);
    }

    public DecodedJWT verifyAndDecode(String jwt) {
        return JWT.require(JWT_ALGORITHM).build().verify(jwt);
    }
}
