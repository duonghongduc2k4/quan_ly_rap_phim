package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
public class MovieRoomChair {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private String status;
    @ManyToMany(cascade = CascadeType.ALL)
//    mã ghế
    @JoinTable(name="Seat_code",
    joinColumns=@JoinColumn(name = "movieRoomChair_id"),
    inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    @JsonManagedReference
    private Set<Ticket> likedTicket;


}
