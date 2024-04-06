package com.codegym.rapphim.repository;
import com.codegym.rapphim.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Iterator;


@Repository
public interface IMovieRepository extends JpaRepository<Movie,Integer> {
 Page<Movie> findByNameMovieContaining(String nameMovie, Pageable pageable);
}
