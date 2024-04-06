package com.codegym.rapphim.service;

import com.codegym.rapphim.model.MovieTimes;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MovieTimesService implements IMovieTimesService{
    @Autowired
private IMovieTimesRepository iMovieTimesRepository;
    @Override
    public Page<MovieTimes> fillAll(Pageable pageable) {
        return iMovieTimesRepository.findAll(pageable);
    }

    @Override
    public Iterable<MovieTimes> fillAll() {
        return iMovieTimesRepository.findAll();
    }

    @Override
    public Optional<MovieTimes> fillById(int id) {
        return iMovieTimesRepository.findById(id);
    }

    @Override
    public void save(MovieTimes movieTimes) {
iMovieTimesRepository.save(movieTimes);
    }

    @Override
    public void remote(int id) {
iMovieTimesRepository.deleteById(id);
    }
}
