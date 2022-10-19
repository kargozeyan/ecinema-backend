package com.ecinema.controller;

import com.ecinema.dto.res.MoviesByGenreDto;
import com.ecinema.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getMovies(Pageable pageable) {
        return ResponseEntity.ok(movieService.getMovies(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getDetails(id));
    }


    @GetMapping("/by_genres")
    public ResponseEntity<?> getByGenres(@RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(movieService.getMoviesByGenres(size));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void editMovie() {

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void postMovie() {

    }
}
