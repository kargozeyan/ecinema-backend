package com.ecinema.repo;

import com.ecinema.domain.Genre;
import com.ecinema.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAllByGenres(Genre genre, Pageable pageable);

}
