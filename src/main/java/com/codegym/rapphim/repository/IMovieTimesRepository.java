package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.Movie;
import com.codegym.rapphim.model.MovieTime;
import com.codegym.rapphim.model.MovieTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieTimesRepository extends JpaRepository<MovieTimes,Integer> {
    Iterable<MovieTimes> findByMovieId(int idMovie);

    Iterable<MovieTimes> findByMovieIdAndTheaterId(int idMovie, int idTheater);

}
