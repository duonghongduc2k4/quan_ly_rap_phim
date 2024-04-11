package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.MovieRoomChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface IMovieRoomChairRepository extends JpaRepository<MovieRoomChair,Integer> {

}
