package com.codegym.rapphim.service;

import java.util.Optional;

public interface IGenerateService <T>{
    Iterable<T> fillAll();
    Optional<T> fillById(int id);
    void save(T t);
    void remote(int id);
}
