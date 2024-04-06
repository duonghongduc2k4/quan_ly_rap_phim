package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.MovieTime;
import com.codegym.rapphim.model.MovieTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieTimesRepository extends JpaRepository<MovieTimes,Integer> {
}
