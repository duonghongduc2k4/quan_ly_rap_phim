package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Integer> {
}
