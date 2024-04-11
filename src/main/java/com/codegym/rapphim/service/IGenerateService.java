package com.codegym.rapphim.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateService <T>{
    Page<T> fillAll(Pageable pageable);
    Iterable<T> fillAll();
    Optional<T> fillById(int id);
    void save(T t);

    void remote(int id);
}
