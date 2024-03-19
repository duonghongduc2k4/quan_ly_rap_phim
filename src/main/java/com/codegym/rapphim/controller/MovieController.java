package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.service.IRoomService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class MovieController {
    @Autowired
    private IRoomService iRoomService;
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/room/index");
        modelAndView.addObject("rooms",iRoomService.fillAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/room/create");
        modelAndView.addObject("room", new Room());
        return modelAndView;
    }
    @PostMapping("/create")
    public String save(@ModelAttribute Room room){
       iRoomService.save(room);
       return "redirect:/room";
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdate(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/room/update");
        Optional<Room> optionalRoom = iRoomService.fillById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            // Chuyển đổi showTimes thành định dạng chuỗi
            LocalDateTime showTimes = room.getShowTimes();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String formattedShowTimes = showTimes.format(formatter);
            // Thêm trường showTimes đã được chuyển đổi vào modelAndView
            modelAndView.addObject("formattedShowTimes", formattedShowTimes);
            modelAndView.addObject("room", room);

        } else {
            // Xử lý trường hợp không tìm thấy phòng với ID tương ứng (ví dụ: hiển thị thông báo lỗi)
            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Room room){
        iRoomService.save(room);
        return "redirect:/room";
    }
    @GetMapping("/remove/{id}")
    public ModelAndView showRemove(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/room/remove");
        Optional<Room> optionalRoom = iRoomService.fillById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            modelAndView.addObject("room", room);
//
        } else {
            // Xử lý trường hợp không tìm thấy phòng với ID tương ứng (ví dụ: hiển thị thông báo lỗi)
            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }
    @PostMapping("/remove")
    public String remove(@ModelAttribute Room room){
        iRoomService.remote(room.getId());
        return "redirect:/room";
    }

}
