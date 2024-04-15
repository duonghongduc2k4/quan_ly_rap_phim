package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "movieTimes_id")
    public MovieTimes movieTimes;

    @ManyToOne
    @JoinColumn(name = "room_id")
    public Room room;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    public Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    public Theater theater;
}
