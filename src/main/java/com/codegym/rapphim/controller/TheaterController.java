package com.codegym.rapphim.controller;


import com.codegym.rapphim.model.Room;

import com.codegym.rapphim.model.Theater;
import com.codegym.rapphim.repository.ITheaterRepository;
import com.codegym.rapphim.service.IRoomService;
import com.codegym.rapphim.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/theater")
public class TheaterController {
  @Autowired
    private ITheaterService iTheaterService;
  @Autowired
  private ITheaterRepository iTheaterRepository;


  @GetMapping("")
    public ModelAndView showMovieTheater(@PageableDefault(7)Pageable pageable){
      ModelAndView modelAndView = new ModelAndView("/theater/index");
      modelAndView.addObject("theaters",iTheaterService.fillAll(pageable));
      return modelAndView;
  }
  @GetMapping("/create")
    public ModelAndView showCreate(){
      ModelAndView modelAndView = new ModelAndView("/theater/create");
      modelAndView.addObject("theater",new Theater());
      return modelAndView;
  }
  @PostMapping("/create")
    public String save(@ModelAttribute Theater theater){
     iTheaterService.save(theater);
      return "redirect:/theater";
  }
  @GetMapping("/update/{id}")
  public ModelAndView showUpdate(@PathVariable int id){
    ModelAndView modelAndView = new ModelAndView("/theater/update");
  Optional<Theater> optionalMovieTheater= iTheaterService.fillById(id);
    if (optionalMovieTheater.isPresent()) {
      Theater theater = optionalMovieTheater.get();
      modelAndView.addObject("theater", theater);

    } else {
      // Xử lý trường hợp không tìm thấy phòng với ID tương ứng (ví dụ: hiển thị thông báo lỗi)
      modelAndView.addObject("errorMessage", "Room not found");
    }
    return modelAndView;
  }
  @PostMapping("/update")
  public String update(@ModelAttribute Theater theater){
    iTheaterService.save(theater);
    return "redirect:/theater";
  }
  @GetMapping("/remote/{id}")
  public ModelAndView showRemove(@PathVariable int id) {
    ModelAndView modelAndView = new ModelAndView("/theater/remote");
    Optional<Theater> optionalMovieTheater = iTheaterService.fillById(id);
    if (optionalMovieTheater.isPresent()) {
     Theater theater = optionalMovieTheater.get();
      modelAndView.addObject("theater", theater);
    } else {
      modelAndView.addObject("errorMessage", "Room not found");
    }
    return modelAndView;
  }
  @PostMapping("/remote")
  public String remove(@ModelAttribute Theater theater){
    iTheaterService.remote(theater.getId());
    return "redirect:/theater";
  }
  @GetMapping("/check")
  public ModelAndView checkByName(@RequestParam String nameTheater,@PageableDefault(5) Pageable pageable){
    ModelAndView modelAndView = new ModelAndView("/theater/check");
    modelAndView.addObject("theaters",iTheaterRepository.findByNameTheaterContaining(nameTheater,pageable));
    modelAndView.addObject("nameTheater",nameTheater);
    return modelAndView;
  }
}
