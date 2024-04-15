package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.*;
import com.codegym.rapphim.model.MovieRoomChair;
import com.codegym.rapphim.repository.IMovieTimeRepository;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import com.codegym.rapphim.repository.ITheaterRepository;
import com.codegym.rapphim.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
    public ITicketService ticketService;

    @ModelAttribute("movies")
    public Iterable<Movie> findAllMovie(){
        return iMovieService.fillAll();
    }
    @ModelAttribute("movieTimes")
    public Iterable<MovieTimes> findAllMovieTimes(){
        return iMovieTimesService.fillAll();
    }
    @ModelAttribute("rooms")
    public Iterable<Room> findAllRoom(){
        return iRoomService.fillAll();
    }
    @ModelAttribute("theaters")
    public Iterable<Theater> findAllTheater(){
        return iTheaterService.fillAll();
    }
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/ticket/index");
        modelAndView.addObject("listTickets", ticketService.fillAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createFormTicket(){
        ModelAndView modelAndView = new ModelAndView("/ticket/create");
        modelAndView.addObject("ticket",new Ticket());
        return modelAndView;
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Ticket ticket){
        ticketService.save(ticket);
        return "redirect:/ticket";
    }



    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Optional<Ticket> ticket = ticketService.fillById(id);
        ModelAndView modelAndView = new ModelAndView("/ticket/update");
        modelAndView.addObject("ticket", ticket.get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.save(ticket);
        ModelAndView modelAndView = new ModelAndView("redirect:/ticket");
        modelAndView.addObject("ticket", ticket);
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/ticket");
        ticketService.remote(id);
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
        modelAndView.addObject("movie",movie);
        modelAndView.addObject("movieTimes",movieTimes);
        modelAndView.addObject("room",room);
        modelAndView.addObject("theater",theater);
        modelAndView.addObject("MovieRoomChair",new MovieRoomChair());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam("idMovieTimes") int idMovieTimes,@RequestParam("idTheater") int idTheater,@RequestParam("idRoom") int idRoom){
        ModelAndView modelAndView = new ModelAndView("/ticket/showTicket");

        Set<MovieRoomChair> movieRoomChairs = new HashSet<>();
        return modelAndView;
    }

}
