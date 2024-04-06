package com.codegym.rapphim.repository;

//import com.codegym.rapphim.controller.MovieTheaterController;

import com.codegym.rapphim.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater,Integer> {
}
