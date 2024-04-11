package com.codegym.rapphim.controller;



import com.codegym.rapphim.model.MovieTime;
import com.codegym.rapphim.service.IMovieTimeService;
import com.codegym.rapphim.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movieTime")
public class MovieTimeController {
    @Autowired
    private IMovieTimeService iMovieTimeService;
    @Autowired

    private ITheaterService iTheaterService;
    @GetMapping("")
    public ModelAndView show(@PageableDefault(10) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/movieTime/index");
        modelAndView.addObject("movieTimes",iMovieTimeService.fillAll(pageable));
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/movieTime/create");
        modelAndView.addObject("movieTime", new MovieTime());
        modelAndView.addObject("theaters",iTheaterService.fillAll());
        return modelAndView;
    }
    @PostMapping("/create")
    public String save(@ModelAttribute MovieTime movieSchedule){
        iMovieTimeService.save(movieSchedule);
        return "redirect:/movieTime";
    }
}
