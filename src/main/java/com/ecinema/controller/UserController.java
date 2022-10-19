package com.ecinema.controller;

import com.ecinema.domain.Movie;
import com.ecinema.domain.User;
import com.ecinema.dto.req.EditRequestDto;
import com.ecinema.dto.res.BalanceDto;
import com.ecinema.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PutMapping
    public void editUser(@Valid @RequestBody EditRequestDto editRequestDto) {
        userService.editUser(editRequestDto);
    }

    @GetMapping("/movies/purchased")
    public List<Movie> getMovies() {
        return userService.getMovies();
    }

    @GetMapping("/movies/favorites")
    public List<Movie> getFavorites() {
        return userService.getFavorites();
    }

    @PostMapping("/movies")
    public void buyMovie(@RequestParam double movieId) {

    }

    @PostMapping("/movies/mark-favorite")
    public void markFavorite(@RequestParam double movieId) {
        userService.markFavorite(2);
    }

    @DeleteMapping("/movies/favorites")
    public void removeFavorite(@RequestParam double movieId) {

    }

    @PutMapping("/balance")
    public ResponseEntity<BalanceDto> refillBalance(@RequestParam double amount) {
        return null;
    }

    @PostMapping("/comments")
    public void postComment(@RequestBody String content) {

    }
}
