package com.codegym.rapphim.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    id
    public int id;
//    tên phim
    public String nameMovie;
//    ảnh
    public String image;
//    ngày khởi chiếu
    public LocalDate launchDate;
//    ngày kết thúc
    public LocalDate endDate;
//    nội dung chính
    public String mainContent;
//    tổng chi phí
    public double totalCost;
//    tổng thu
    public double totalRevenue;
//    đạo diễn
    public String category;

    public Movie(int id, String nameMovie, String image, LocalDate launchDate, LocalDate endDate, String mainContent, double totalCost, double totalRevenue, String category) {
        this.id = id;
        this.nameMovie = nameMovie;
        this.image = image;
        this.launchDate = launchDate;
        this.endDate = endDate;
        this.mainContent = mainContent;
        this.totalCost = totalCost;
        this.totalRevenue = totalRevenue;
        this.category = category;
    }

    public Movie() {

    }
}
