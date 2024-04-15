package com.codegym.rapphim.service;

import com.codegym.rapphim.model.MovieTimes;
import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.model.Ticket;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import com.codegym.rapphim.repository.IRoomRepository;
import com.codegym.rapphim.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository iTicketRepository;
    @Autowired
    private IRoomRepository iRoomRepository;
    @Autowired
    private IMovieTimesRepository iMovieTimesRepository;

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
        return iTicketRepository.findById(id);
    }

//    @Override
//    public void save(Ticket ticket) {
//        iTicketRepository.save(ticket);
//    }

    @Override
    public void save(Ticket ticket) {
        if (ticket.getId() == 0) {
            int movieTimesId = ticket.getMovieTimes().getId();
            Optional<MovieTimes> optionalMovieTimes = iMovieTimesRepository.findById(movieTimesId);
            if (optionalMovieTimes.isPresent()) {
                MovieTimes movieTimes = optionalMovieTimes.get();
                int roomId = ticket.getRoom().getId();
                Optional<Room> optionalRoom = iRoomRepository.findById(roomId);
                if (optionalRoom.isPresent()) {
                    Room room = optionalRoom.get();
                    if (movieTimes.getNumberOfTicketsSold() >= 0) {
                        // Giảm số ghế trống đi 1
                        room.setNumberOfSeats(room.getNumberOfSeats() - 1);
                        // Tăng số vé đã bán lên 1
                        movieTimes.setNumberOfTicketsSold(movieTimes.getNumberOfTicketsSold() + 1);
                        // Cập nhật thông tin
                        iTicketRepository.save(ticket);
                        iMovieTimesRepository.save(movieTimes);
                        iRoomRepository.save(room);
                    }
                }
            }
        }else {
            iTicketRepository.save(ticket);
        }
    }


    @Override
    public void remote(int id) {
        iTicketRepository.deleteById(id);
    }
}
