package com.codegym.rapphim.repository;



import com.codegym.rapphim.model.MovieTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieTimeRepository extends JpaRepository<MovieTime,Integer> {
}
