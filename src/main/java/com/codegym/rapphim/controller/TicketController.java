package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.*;

import com.codegym.rapphim.repository.IMovieTimeRepository;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import com.codegym.rapphim.repository.IRoomRepository;
import com.codegym.rapphim.repository.ITheaterRepository;
import com.codegym.rapphim.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/ticket")
public class TicketController {
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
    public IRoomRepository iRoomRepository;


    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/ticket/showMovies");
        modelAndView.addObject("movies",iMovieService.fillAll() );
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Movie movie : iMovieService.fillAll()) {
            imagePaths.add(movie.getImage());
            imageNames.add(movie.nameMovie);
        }
        modelAndView.addObject("imagePaths", imagePaths);
        modelAndView.addObject("imageNames", imageNames);
        return modelAndView;
    }

    @GetMapping("/showMovie/{id}")
    public ModelAndView showMovie(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/ticket/showMovie");
        Optional<Movie> movie = iMovieService.fillById(id);
        modelAndView.addObject("movie", movie.get());
        return modelAndView;
    }

    @GetMapping("/showMovieTimes")
    public ModelAndView showMovieTimes(@RequestParam(value = "idMovie", required = false) Integer idMovie, @RequestParam(value = "idTheater", required = false) Integer idTheater) {

        ModelAndView modelAndView = new ModelAndView("/ticket/showMovieTimes");
        Iterable<MovieTimes> movieTimes;
        Iterable<Theater> theaters = iTheaterRepository.findByTheaterId();
        modelAndView.addObject("theaters", theaters);
        if (idTheater != null) {
            Integer storedIdMovie = (Integer) httpSession.getAttribute("idMovie");
            movieTimes = iMovieTimesRepository.findByMovieIdAndTheaterId(storedIdMovie, idTheater);
        } else {
            httpSession.setAttribute("idMovie", idMovie);
            movieTimes = iMovieTimesRepository.findByMovieId(idMovie);
        }
        modelAndView.addObject("movieTimes", movieTimes);

        return modelAndView;
    }

    @GetMapping("/create/{id}")
    public ModelAndView create(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/ticket/showTicket");
        Optional<MovieTimes> movieTimes = iMovieTimesRepository.findById(id);
        Optional<Room> room = iRoomService.fillById(movieTimes.get().getRoom().getId());
        Optional<Theater> theater = iTheaterService.fillById(movieTimes.get().getTheater().getId());
        Optional<Movie> movie = iMovieService.fillById(movieTimes.get().getMovie().getId());
        modelAndView.addObject("movie", movie);
        modelAndView.addObject("movieTimes", movieTimes);
        modelAndView.addObject("room", room);
        modelAndView.addObject("theater", theater);
        modelAndView.addObject("ticket", new Ticket());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Ticket ticket,@RequestParam("idMovieTimes") int idMovieTimes) {
        MovieTimes movieTimes = iMovieTimesService.fillById(idMovieTimes).get();
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
        return "redirect:/ticket";
    }

}
