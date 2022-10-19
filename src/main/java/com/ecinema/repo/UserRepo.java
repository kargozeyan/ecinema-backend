package com.ecinema.repo;

import com.ecinema.domain.Movie;
import com.ecinema.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // change to Optional
    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
            FROM Movie m
                     INNER JOIN FavoriteMovie FM on m.id = FM.movieId
                     INNER JOIN User U on U.id = FM.userId AND U.id = :userId
            """)
    List<Movie> findFavoriteMovies(@Param("userId") long userId);

    @Query("""
            SELECT m
            FROM Movie m
                 JOIN UserMovie FM on m.id = FM.movieId
                 JOIN User U on U.id = FM.userId AND U.id = :userId
                """)
    List<Movie> findUserMovies(@Param("userId") long userId);
}
