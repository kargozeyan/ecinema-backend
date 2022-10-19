package com.ecinema.service.movie;

import com.ecinema.domain.Movie;
import com.ecinema.dto.res.MoviesByGenreDto;
import com.ecinema.repo.GenreRepo;
import com.ecinema.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final GenreRepo genreRepo;

    @Override
    public List<MoviesByGenreDto> getMoviesByGenres(int size) {

        return genreRepo.findAll().stream()
                .map(genre -> new MoviesByGenreDto(
                        movieRepo.findAllByGenres(genre, Pageable.ofSize(size)),
                        genre
                )).toList();
    }

    @Override
    public List<Movie> getMovies(Pageable pageable) {
        return movieRepo.findAll(pageable).toList();
    }

    @Override
    public Movie getDetails(Long id) {
        return movieRepo.findById(id).orElseThrow();
    }
}
