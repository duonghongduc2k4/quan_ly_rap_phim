package com.codegym.rapphim.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
public class MovieFile {
    public int id;
    public String nameMovie;
    public MultipartFile image;
    //    ngày khởi chiếu
    public LocalDate launchDate;
    //    ngày kết thúc
    public LocalDate endDate;
    //    nội dung chính

    public String mainContent;
    //    tổng chi phí

    public double totalRevenue;
    //    đạo diễn
    public String category;

    public MovieFile(int id, String nameMovie, MultipartFile image, LocalDate launchDate, LocalDate endDate, String mainContent, double totalRevenue, String category) {
        this.id = id;
        this.nameMovie = nameMovie;
        this.image = image;
        this.launchDate = launchDate;
        this.endDate = endDate;
        this.mainContent = mainContent;

        this.totalRevenue = totalRevenue;
        this.category = category;
    }

    public MovieFile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }





    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
