package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.Movie;
import com.codegym.rapphim.model.MovieTime;
import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.model.Ticket;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findByRoomNameContaining(String roomName, Pageable pageable);
    Optional<Room> findById(int roomId);
    Iterable<Room> findAllByLikes(Set<Ticket> users);
}
