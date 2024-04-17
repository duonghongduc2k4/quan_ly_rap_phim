package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Transactional
public interface ITicketRepository extends JpaRepository<Ticket,Integer> {
Ticket findByMovieTimesId(int idMovieTimes);
Iterable<Ticket> findAllByLikedRoom(Set<Room> rooms);
@Modifying
    @Query(value = "delete from seat_code where ticket_id =:value1 and room_id =:value2",nativeQuery =true)
    public void DeleteByIdTicketAndRoom(@Param("value1") int idTicket,@Param("value2") int idRoom);
}
