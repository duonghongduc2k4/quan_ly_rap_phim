package com.codegym.rapphim.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "movieTime")
public class MovieTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime showtime;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
}
