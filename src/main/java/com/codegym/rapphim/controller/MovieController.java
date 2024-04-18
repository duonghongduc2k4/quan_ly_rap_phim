package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.*;
import com.codegym.rapphim.repository.IMovieRepository;
import com.codegym.rapphim.repository.IMovieTimesRepository;
import com.codegym.rapphim.repository.ITicketRepository;
import com.codegym.rapphim.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.catalina.manager.JspHelper.formatNumber;

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
    @Autowired
    private ITicketRepository iTicketRepository;
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private IRoomService iRoomService;

    @GetMapping("")
    public ModelAndView index(@PageableDefault(2) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/movie/index");
        Page<Movie> moviePage = iMovieService.fillAll(pageable);
        modelAndView.addObject("movies", moviePage);
        return modelAndView;
    }

    @GetMapping("/check")
    public ModelAndView showByName(@RequestParam String nameMovie, @PageableDefault(2) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/movie/showByName");
        Page<Movie> moviePage = iMovieRepository.findByNameMovieContaining(nameMovie, pageable);
        modelAndView.addObject("movies", moviePage);
        modelAndView.addObject("nameMovie",nameMovie);
        return modelAndView;

    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/movie/create");
        modelAndView.addObject("movieFile", new MovieFile());
        return modelAndView;
    }

    @PostMapping("/create")
    public String save(@ModelAttribute MovieFile movieFile) throws IOException {
        MultipartFile multipartFile = movieFile.getImage();
        String fileName = multipartFile.getOriginalFilename();

        if (multipartFile.isEmpty()) {
            // Không có tệp ảnh mới được chọn, sử dụng ảnh gốc
            Movie originalMovie = iMovieService.fillById(movieFile.getId()).get();
            Movie movie = new Movie(movieFile.getId(), movieFile.getNameMovie(), originalMovie.getImage(), movieFile.getLaunchDate(), movieFile.getEndDate(), movieFile.getMainContent(), movieFile.getTotalRevenue(), movieFile.getCategory());
            iMovieService.save(movie);
        } else {
            // Có tệp ảnh mới được chọn, sao chép và cập nhật thông tin của phim
            try {
                FileCopyUtils.copy(movieFile.getImage().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Movie movie = new Movie(movieFile.getId(), movieFile.getNameMovie(), "img/" + fileName, movieFile.getLaunchDate(), movieFile.getEndDate(), movieFile.getMainContent(), movieFile.getTotalRevenue(), movieFile.getCategory());
            iMovieService.save(movie);
        }

        return "redirect:/movie";
    }



    @GetMapping("/update/{id}")
    public ModelAndView showUpdate(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/movie/update");
        Optional<Movie> optionalMovie = iMovieService.fillById(id);
       MovieFile movieFile = new MovieFile(optionalMovie.get().getId(),optionalMovie.get().getNameMovie(),new MovieFile().getImage(),optionalMovie.get().getLaunchDate(),optionalMovie.get().getEndDate(),optionalMovie.get().getMainContent(),optionalMovie.get().getTotalRevenue(),optionalMovie.get().getCategory());
        modelAndView.addObject("movieFile", movieFile);
        String path = optionalMovie.get().image;
        modelAndView.addObject("path", path);
        return modelAndView;
    }

    @GetMapping("/remote/{id}")
    public ModelAndView showRemove(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/movie/remote");

        Optional<Movie> optionalRoom = iMovieService.fillById(id);
        if (optionalRoom.isPresent()) {
            Movie movie = optionalRoom.get();
            modelAndView.addObject("movie", movie);
        } else {
            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }

    @PostMapping("/remote")
    public String remove(@ModelAttribute Movie movie) {
//        int id = movie.getId();
//        Iterable<MovieTimes> movieTimes = iMovieTimesRepository.findByMovieId(id);
//        for (MovieTimes movieTimes1 : movieTimes) {
//            Room room1 = iRoomService.fillById(movieTimes1.getRoom().getId()).get();
//
//                Iterable<Ticket> tickets = iTicketRepository.findAllByLikedRoom(Collections.singleton(room1));
//                for (Ticket ticket : tickets) {
//                    iTicketRepository.DeleteByIdTicketAndRoom(ticket.getId(),room1.getId());
//                    iTicketService.remote(id);
//                    iMovieTimesService.remote(ticket.getMovieTimes().getId());
//            }


//            iMovieTimesService.remote(movieTimes1.getId());
//        }
//        iMovieService.remote(id);
        return "redirect:/movie";
    }
}




