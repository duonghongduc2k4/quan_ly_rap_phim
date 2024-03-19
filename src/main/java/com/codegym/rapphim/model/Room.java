package com.codegym.rapphim.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomName;
    private int numberOfSeats;
    private int maximumNumberOfSeats;
    private LocalDateTime showTimes;

}
