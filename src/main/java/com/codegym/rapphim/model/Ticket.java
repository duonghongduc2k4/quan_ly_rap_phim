package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Entity
@Data
public class Ticket {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "movieTimes_id")
    public MovieTimes movieTimes;
    @ManyToMany(mappedBy = "likedTicket")
    @JsonBackReference
    private Set<MovieRoomChair> likes;

}
