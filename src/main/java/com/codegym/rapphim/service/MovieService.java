package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Movie;
//import com.codegym.rapphim.model.MovieTheater;
//import com.codegym.rapphim.model.Room_Movie;
import com.codegym.rapphim.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MovieService implements IMovieService{


    @Autowired
    private IMovieRepository iMovieRepository;
    @Override
    public Iterable<Movie> fillAll() {
        return iMovieRepository.findAll();
    }


    @Override
    public Page<Movie> fillAll(Pageable pageable) {
        return iMovieRepository.findAll(pageable);
    }

    @Override
    public Optional<Movie> fillById(int id) {
        return iMovieRepository.findById(id);
    }

    @Override
    public void save(Movie movie) {
iMovieRepository.save(movie);
    }

    @Override
    public void remote(int id) {
iMovieRepository.deleteById(id);
    }


    @Override
    public Page<Movie> findByNameMovieContaining(String nameMovie,Pageable pageable) {
        return iMovieRepository.findByNameMovieContaining(nameMovie,pageable);
    }
}
