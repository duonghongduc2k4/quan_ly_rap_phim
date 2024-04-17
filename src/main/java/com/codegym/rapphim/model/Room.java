package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "ticket_id")
    private Theater theater;
    @ManyToMany(mappedBy = "likedRoom")
    @JsonBackReference
    private Set<Ticket> likes;

}
