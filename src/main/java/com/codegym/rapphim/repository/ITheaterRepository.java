package com.codegym.rapphim.repository;

//import com.codegym.rapphim.controller.MovieTheaterController;

import com.codegym.rapphim.model.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater,Integer> {
    @Modifying
    @Query(value = "select * from theater where id in (select theater_id from movie_times)",nativeQuery = true)
    Iterable<Theater> findByTheaterId();
    Page<Theater> findByNameTheaterContaining(String nameTheater, Pageable pageable);
}
