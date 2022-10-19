package com.ecinema.service.user;

import com.ecinema.domain.Movie;
import com.ecinema.domain.User;
import com.ecinema.dto.req.EditRequestDto;
import com.ecinema.repo.UserRepo;
import com.ecinema.security.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Movie> getMovies() {
        return userRepo.findUserMovies(getCurrentUser().getId());
    }


    @Override
    public List<Movie> getFavorites() {
        return userRepo.findFavoriteMovies(getCurrentUser().getId());
    }

    @Override
    public void markFavorite(long movieId) {
//        UserDetails user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("markFavorite:" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email);
    }

    @Override
    public void editUser(EditRequestDto dto) {
        User currentUser = getCurrentUser();
        if (passwordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            return;
        }

        currentUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepo.save(currentUser);
    }
}
