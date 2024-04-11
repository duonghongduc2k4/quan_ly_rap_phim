package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.Movie;
import com.codegym.rapphim.model.MovieTimes;
import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/movieTimes")
public class MovieTimesController {
    @Autowired
    private IMovieTimesService iMovieTimesService;
    @Autowired
    private IMovieTimeService iMovieTimeService;
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IRoomService iRoomService;
    @Autowired
    private ITheaterService iTheaterService;
@GetMapping("")
public ModelAndView show(@PageableDefault(4) Pageable pageable){
    ModelAndView modelAndView = new ModelAndView("/movieTimes/index");
    modelAndView.addObject("movieTimes",iMovieTimesService.fillAll(pageable));
    return modelAndView;
}
@GetMapping("/create")
    public ModelAndView create(){
    ModelAndView modelAndView = new ModelAndView("/movieTimes/create");
    modelAndView.addObject("movieTimes",new MovieTimes());
    modelAndView.addObject("movies", iMovieService.fillAll()); // Use "movies" instead of "movie"
    modelAndView.addObject("theaters", iTheaterService.fillAll()); // Use "theaters" instead of "theater"
    modelAndView.addObject("rooms", iRoomService.fillAll()); // Use "rooms" instead of "room"
    modelAndView.addObject("movieTimeList",iMovieTimeService.fillAll());
    return modelAndView;
}
@PostMapping("/create")
    public String save(@ModelAttribute MovieTimes movieTimes){
    System.out.println(movieTimes);
    iMovieTimesService.save(movieTimes);
    return "redirect:/movieTimes";

}
@GetMapping("/update/{id}")
public ModelAndView showUpdate(@PathVariable int id){
    ModelAndView modelAndView = new ModelAndView("/movieTimes/update");
    modelAndView.addObject("movies", iMovieService.fillAll()); // Use "movies" instead of "movie"
    modelAndView.addObject("theaters", iTheaterService.fillAll()); // Use "theaters" instead of "theater"
    modelAndView.addObject("rooms", iRoomService.fillAll()); // Use "rooms" instead of "room"
    modelAndView.addObject("movieTimeList",iMovieTimeService.fillAll());
    modelAndView.addObject("movieTimes",iMovieTimesService.fillById(id).get());
    return modelAndView;
}
    @PostMapping("/update")
    public String update(@ModelAttribute MovieTimes movieTimes){
        iMovieTimesService.save(movieTimes);
        return "redirect:/movieTimes";

    }
    @GetMapping("/remote/{id}")
    public ModelAndView showRemove(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/movieTimes/remote");
        Optional<MovieTimes> optionalRoom = iMovieTimesService.fillById(id);
        if (optionalRoom.isPresent()) {
            MovieTimes movieTimes = optionalRoom.get();
            modelAndView.addObject("movieTimes", movieTimes);
        } else {
            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }
    @PostMapping("/remote")
    public String remove(@ModelAttribute  MovieTimes movieTimes){

        iMovieTimesService.remote(movieTimes.getId());
        return "redirect:/room";
    }
}
