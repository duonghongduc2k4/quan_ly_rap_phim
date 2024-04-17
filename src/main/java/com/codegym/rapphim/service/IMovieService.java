package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Movie;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import com.codegym.rapphim.model.MovieTheater;

//import com.codegym.rapphim.model.Room_Movie;

public interface IMovieService extends IGenerateService<Movie>{
    Page<Movie> findByNameMovieContaining(String nameMovie, Pageable pageable);


}
