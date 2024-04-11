package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.*;
import com.codegym.rapphim.repository.IMovieRepository;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import com.codegym.rapphim.service.*;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Value("E:/java/rapPhim/src/main/resources/static/img/")
    private String fileUpload;
    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IMovieTimesRepository iMovieTimesRepository;
    @Autowired
    private IMovieTimesService iMovieTimesService;
    @Autowired
    private IMovieRepository iMovieRepository;
    @GetMapping("")
    public ModelAndView index(@PageableDefault(1)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/movie/index");
        Page<Movie> moviePage = iMovieService.fillAll(pageable);
        modelAndView.addObject("movies",moviePage);
        List<String> imagePaths = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (Movie movie : moviePage){
            imagePaths.add(movie.getImage());
            imageNames.add(movie.nameMovie);
        }
        modelAndView.addObject("imagePaths",imagePaths);
        modelAndView.addObject("imageNames",imageNames);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/movie/create");
        modelAndView.addObject("movieFile", new MovieFile());
        return modelAndView;
    }
    @PostMapping("/create")
    public String save(@ModelAttribute MovieFile movieFile){
        MultipartFile multipartFile = movieFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(movieFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Movie movie = new Movie(movieFile.getId(),movieFile.getNameMovie(),"img/" + fileName,movieFile.getLaunchDate(),movieFile.getEndDate(),movieFile.getMainContent(),movieFile.getTotalCost(),movieFile.getTotalRevenue(),movieFile.getCategory());
        iMovieService.save(movie);
       return "redirect:/movie";
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdate(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/movie/update");
        Optional<Movie> optionalMovie = iMovieService.fillById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            modelAndView.addObject("movie", movie);
            // Thêm đường dẫn ảnh và tên ảnh vào model
            modelAndView.addObject("imagePath", movie.getImage());
            modelAndView.addObject("imageName", movie.getNameMovie());
        } else {
            modelAndView.addObject("errorMessage", "Movie not found");
        }
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Movie movie){
//           MultipartFile multipartFile = movieFile.getImage();
//                String fileName = multipartFile.getOriginalFilename();
//                try {
//                    FileCopyUtils.copy(movieFile.getImage().getBytes(), new File(fileUpload + fileName));
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                Movie movie = new Movie(movieFile.getId(),movieFile.getNameMovie(),"img/" + fileName,movieFile.getLaunchDate(),movieFile.getEndDate(),movieFile.getMainContent(),movieFile.getTotalCost(),movieFile.getTotalRevenue(),movieFile.getCategory());
//                iMovieService.save(movie);
//               return "redirect:/movie";

        iMovieService.save(movie);
        return "redirect:/movie";
    }
    @GetMapping("/remote/{id}")
    public ModelAndView showRemove(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/movie/remote");
        Optional<Movie> optionalRoom = iMovieService.fillById(id);
        if (optionalRoom.isPresent()) {
            Movie movie = optionalRoom.get();
            modelAndView.addObject("movie", movie);
//
        } else {
            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }
    @PostMapping("/remote")
    public String remove(@ModelAttribute Movie movie){
        int id = movie.getId();

      Iterable<MovieTimes> movieTimes =  iMovieTimesRepository.findByMovieId(id);
for (MovieTimes movieTimes1 : movieTimes){
    iMovieTimesService.remote(movieTimes1.getId());
}
        iMovieService.remote(id);
        return "redirect:/movie";
    }
    @GetMapping("/check")
    public ModelAndView showByName(@RequestParam String nameMovie ,@PageableDefault(2) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/movie/showByName");
        Iterable<Movie> movies=iMovieRepository.findByNameMovieContaining(nameMovie,pageable);
        modelAndView.addObject("movies",movies);
        modelAndView.addObject("nameMovie",nameMovie);
return modelAndView;
    }
    }



