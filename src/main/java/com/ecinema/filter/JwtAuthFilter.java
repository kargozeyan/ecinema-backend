package com.ecinema.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecinema.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty() || authHeader.isBlank() || !authHeader.startsWith("Bearer ") || authHeader.length() == "Bearer null".length()) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring("Bearer ".length());
        DecodedJWT decodedJWT = jwtTokenProvider.verifyAndDecode(jwt);
        String email = decodedJWT.getSubject();

        List<SimpleGrantedAuthority> roles = decodedJWT.getClaim("ROLES").asList(String.class).stream().map(SimpleGrantedAuthority::new).toList();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, roles);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
