package com.codegym.rapphim.repository;

import com.codegym.rapphim.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Integer> {
}
