package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id phòng
    private int id;
    // Tên phong Phim
    private String roomName;
    // Số lượng ghế
    private int numberOfSeats;
    // rạp
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;


}
