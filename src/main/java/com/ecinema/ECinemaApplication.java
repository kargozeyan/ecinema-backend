package com.ecinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECinemaApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner cmd(MovieRepository movieRepository, GenreRepository genreRepository) {
//        return args -> {
//            genreRepository.findAll().forEach(it -> {
//                System.out.println(it.getName() + it.getMovies());
//            });
//        };
//    }
}
