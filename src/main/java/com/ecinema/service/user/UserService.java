package com.ecinema.service.user;

import com.ecinema.domain.Movie;
import com.ecinema.domain.User;
import com.ecinema.dto.req.EditRequestDto;

import java.util.List;

public interface UserService {
    List<Movie> getMovies();
    List<Movie> getFavorites();

    void markFavorite(long movieId);

    User getCurrentUser();

    void editUser(EditRequestDto dto);
}
