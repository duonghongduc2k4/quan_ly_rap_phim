package com.codegym.rapphim.controller;

import com.codegym.rapphim.model.Room;
import com.codegym.rapphim.model.Ticket;
import com.codegym.rapphim.repository.IRoomRepository;
import com.codegym.rapphim.repository.ITicketRepository;
import com.codegym.rapphim.service.IRoomService;
import com.codegym.rapphim.service.ITicketService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin(value ="*")
public class test {
    @Autowired
    private ITicketService iTicketService;
    @Autowired
    private IRoomRepository iRoomRepository;
    @Autowired
    private ITicketRepository iTicketRepository;
    @Autowired
    private IRoomService iRoomService;
    @GetMapping("")
    public ResponseEntity<List<Ticket>> testShow() {
        List<Ticket> result = new ArrayList<>();
        Iterable<Room> roomIterable = iRoomService.fillAll();
        for (Room room : roomIterable) {
            Iterable<Ticket> tickets = iTicketRepository.findAllByLikedRoom(Collections.singleton(room));
            for (Ticket ticket : tickets) {
result.add(ticket);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/remote/{id}")
    public ResponseEntity<String> removeLikedRooms(@PathVariable int id) {
        Iterable<Room> roomIterable = iRoomService.fillAll();
        for (Room room : roomIterable) {
          iTicketRepository.DeleteByIdTicketAndRoom(id,room.getId());
        }
        return new ResponseEntity<>("Da xoa", HttpStatus.OK); // Trả về ticket đã được xử lý thành công với mã lỗi 200
    }
}
