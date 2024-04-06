package com.codegym.rapphim.controller;

import com.codegym.rapphim.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public ModelAndView show(@PageableDefault(10) Pageable pageable){
    ModelAndView modelAndView = new ModelAndView("");
    modelAndView.addObject("movieTimes",iMovieTimesService.fillAll(pageable));
    return modelAndView;
}
}
