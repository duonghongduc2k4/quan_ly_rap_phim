package com.codegym.rapphim.service;

import com.codegym.rapphim.model.Ticket;
import com.codegym.rapphim.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TicketService implements ITicketService{
    @Autowired
    private ITicketRepository iTicketRepository;
    @Override
    public Page<Ticket> fillAll(Pageable pageable) {
        return iTicketRepository.findAll(pageable);
    }

    @Override
    public Iterable<Ticket> fillAll() {
        return iTicketRepository.findAll();
    }

    @Override
    public Optional<Ticket> fillById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Ticket ticket) {
iTicketRepository.save(ticket);
    }

    @Override
    public void remote(int id) {

    }
}
