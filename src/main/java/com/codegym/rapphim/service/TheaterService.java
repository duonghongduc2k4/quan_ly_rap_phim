package com.codegym.rapphim.service;


import com.codegym.rapphim.model.Theater;
import com.codegym.rapphim.repository.ITheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterService implements ITheaterService{
    @Autowired
    private ITheaterRepository iTheaterRepository;


    @Override
    public Page<Theater> fillAll(Pageable pageable) {
        return iTheaterRepository.findAll(pageable);
    }

    @Override
    public Iterable<Theater> fillAll() {
        return iTheaterRepository.findAll();
    }

    @Override
    public Optional<Theater> fillById(int id) {
        return iTheaterRepository.findById(id);
    }
    @Override
    public void save(Theater theater) {
iTheaterRepository.save(theater);
    }

    @Override
    public void remote(int id) {
iTheaterRepository.deleteById(id);
    }
}
