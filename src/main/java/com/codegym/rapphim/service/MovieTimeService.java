package com.codegym.rapphim.service;

import com.codegym.rapphim.model.MovieTime;
import com.codegym.rapphim.repository.IMovieTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MovieTimeService implements IMovieTimeService {
    @Autowired
    private IMovieTimeRepository iMovieTimeRepository;
    @Override
    public Page<MovieTime> fillAll(Pageable pageable) {
        return iMovieTimeRepository.findAll(pageable);
    }


    @Override
    public Iterable<MovieTime> fillAll() {
        return iMovieTimeRepository.findAll();
    }

    @Override
    public Optional<MovieTime> fillById(int id) {
        return iMovieTimeRepository.findById(id);
    }

    @Override
    public void save(MovieTime movieSchedule) {
        iMovieTimeRepository.save(movieSchedule);
    }

    @Override
    public void remote(int id) {

    }
}
