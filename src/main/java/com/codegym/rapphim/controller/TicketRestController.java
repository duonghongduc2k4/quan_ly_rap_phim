package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.*;
import com.codegym.rapphim.repository.*;
import com.codegym.rapphim.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/api/ticketRestController")
public class TicketRestController {
    @Autowired
    private IMovieTimesService iMovieTimesService;
    @Autowired
    private IMovieTimesRepository iMovieTimesRepository;
    @Autowired
    private IMovieTimeRepository iMovieTimeRepository;
    @Autowired
    private IMovieTimeService iMovieTimeService;
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IRoomService iRoomService;
    @Autowired
    private ITheaterService iTheaterService;
    @Autowired
    public HttpSession httpSession;
    @Autowired
    public ITheaterRepository iTheaterRepository;

    @Autowired
    public ITicketService iTicketService;
    @Autowired
    public ITicketRepository iTicketRepository;
    @Autowired
    public IRoomRepository iRoomRepository;
//    @GetMapping("")
//    public ResponseEntity<Iterable<Movie>> index() {
//        List<Movie> ticketIterable = (List<Movie>) iMovieService.fillAll();
//        if (ticketIterable.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(ticketIterable, HttpStatus.OK);
//    }
@GetMapping("")
public ResponseEntity<Iterable<Ticket>> testShow() {
    List<Ticket> result = new ArrayList<>();
    Iterable<Room> roomIterable = iRoomService.fillAll();
    for (Room room : roomIterable) {
        Iterable<Ticket> tickets = iTicketRepository.findAllByLikedRoom(Collections.singleton(room));
        for (Ticket ticket : tickets) {
            result.add(ticket);
        }
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<Movie> showMovie(@PathVariable int id) {
        Movie movieOptional = iMovieService.fillById(id).get();
        if (movieOptional==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movieOptional, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<String> saveTecket(@RequestBody Ticket ticket) {
        MovieTimes movieTimes = iMovieTimesService.fillById(ticket.getMovieTimes().getId()).get();
        int numberOfTicketsSold = (int)movieTimes.getNumberOfTicketsSold()+1;
        double totalMoney = movieTimes.getTotalMoney()+movieTimes.getFare();
        movieTimes = new MovieTimes(movieTimes.getId(),movieTimes.getMovie(),movieTimes.getTheater(),movieTimes.getRoom(),movieTimes.getShowDate(),movieTimes.getMovieTime(),movieTimes.getFare(),numberOfTicketsSold,totalMoney);
        iMovieTimesService.save(movieTimes);
        Room room = iRoomService.fillById(movieTimes.getRoom().getId()).get();
        ticket = new Ticket(ticket.getId(),movieTimes,ticket.getLikedRoom());
        Ticket ticket1 = iTicketService.saveTicket(ticket);
        Set<Room> roomSet = new HashSet<>();
        roomSet.add(room);
        ticket1.setLikedRoom(roomSet);
        this.iTicketService.save(ticket1);
        Movie movie = iMovieService.fillById(movieTimes.getMovie().getId()).get();
        int totalRevenue = (int) (movie.getTotalRevenue() + movieTimes.getFare());
        movie = new Movie(movie.getId(), movie.getNameMovie(), movie.getImage(), movie.getLaunchDate(), movie.getEndDate(), movie.getMainContent(), totalRevenue, movie.getCategory());
        iMovieService.save(movie);
        return new ResponseEntity<>("Thêm thành công", HttpStatus.CREATED);
    }
}
