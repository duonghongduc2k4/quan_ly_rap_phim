package com.codegym.rapphim.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "movieTimes")
public class MovieTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private LocalDate showDate;
    @ManyToOne
    @JoinColumn(name = "movieTime_id")
    private MovieTime movieTime;
    private double fare;
    private int numberOfTicketsSold;
    private double totalMoney;



}
