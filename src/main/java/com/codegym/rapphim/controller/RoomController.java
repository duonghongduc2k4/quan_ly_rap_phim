package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.service.IRoomService;
import com.codegym.rapphim.service.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private ITheaterService iTheaterService;
    @Autowired
    private IRoomService iRoomService;
    @GetMapping("")
    public ModelAndView index(@PageableDefault(7)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/room/index");
        modelAndView.addObject("rooms",iRoomService.fillAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("/room/create");
        modelAndView.addObject("room", new Room());
        modelAndView.addObject("theaters",iTheaterService.fillAll());
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
            modelAndView.addObject("theaters",iTheaterService.fillAll());
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
    @GetMapping("/remote/{id}")
    public ModelAndView showRemove(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/room/remote");
        System.out.println(id);
        Optional<Room> optionalRoom = iRoomService.fillById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            modelAndView.addObject("room", room);
        } else {

            modelAndView.addObject("errorMessage", "Room not found");
        }
        return modelAndView;
    }
    @PostMapping("/remote")
    public String remove(@ModelAttribute ("room") Room room){
        int id = room.getId();
        iRoomService.remote(id);
        return "redirect:/room";
    }
    @GetMapping("/check")
    public ModelAndView checkByName(@RequestParam String roomName,@PageableDefault(5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/room/showByName");
        modelAndView.addObject("rooms",iRoomService.findByRoomNameContaining(roomName,pageable));
        modelAndView.addObject("roomName",roomName);
        return modelAndView;
}
}
