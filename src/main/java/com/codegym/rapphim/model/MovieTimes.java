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
//    Phim
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
//    rạp
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
//    phòng
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
//    Ngày chiếu
    private LocalDate showDate;
//    giờ chiếu
    @ManyToOne
    @JoinColumn(name = "movieTime_id")
    private MovieTime movieTime;
//giá vé
    private double fare;
//    Số lượng vé đã bán
    private int numberOfTicketsSold;
//Tổng số tiền
    private double totalMoney;

    public MovieTimes(int id, Movie movie, Theater theater, Room room, LocalDate showDate, MovieTime movieTime, double fare, int numberOfTicketsSold, double totalMoney) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.room = room;
        this.showDate = showDate;
        this.movieTime = movieTime;
        this.fare = fare;
        this.numberOfTicketsSold = numberOfTicketsSold;
        this.totalMoney = totalMoney;
    }

    public MovieTimes() {
    }
}
