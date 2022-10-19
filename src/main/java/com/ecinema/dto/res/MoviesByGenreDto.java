package com.ecinema.dto.res;

import com.ecinema.domain.Genre;
import com.ecinema.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MoviesByGenreDto {
    private List<Movie> movies;
    private Genre genre;
}
