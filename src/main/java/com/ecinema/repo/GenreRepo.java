package com.ecinema.repo;

import com.ecinema.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

public interface GenreRepo extends JpaRepository<Genre, Integer> {

}
