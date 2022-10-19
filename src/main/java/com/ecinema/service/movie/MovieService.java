package com.ecinema.service.movie;

import com.ecinema.domain.Movie;
import com.ecinema.dto.res.MoviesByGenreDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<MoviesByGenreDto> getMoviesByGenres(int size);

    Movie getDetails(Long id);

    List<Movie> getMovies(Pageable pageable);
}
