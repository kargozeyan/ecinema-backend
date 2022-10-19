package com.ecinema.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FavoriteMovie {
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

}
