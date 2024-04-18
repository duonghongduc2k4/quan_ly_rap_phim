package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Seat_code",
            joinColumns=@JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    @JsonManagedReference
    private Set<Room> likedRoom;

    public Ticket(int id, MovieTimes movieTimes, Set<Room> likedRoom) {
        this.id = id;
        this.movieTimes = movieTimes;
        this.likedRoom = likedRoom;
    }

    public Ticket() {
    }

    public Set<Room> getLikedRoom() {
        return likedRoom;
    }

    public void setLikedRoom(Set<Room> likedRoom) {
        this.likedRoom = likedRoom;
    }
}
