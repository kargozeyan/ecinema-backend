package com.ecinema.domain;

import javax.persistence.*;

@Entity
public class UserMovie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "rating")
    private Integer rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMovie userMovie = (UserMovie) o;

        if (id != userMovie.id) return false;
        if (userId != userMovie.userId) return false;
        if (movieId != userMovie.movieId) return false;
        if (rating != null ? !rating.equals(userMovie.rating) : userMovie.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + movieId;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
