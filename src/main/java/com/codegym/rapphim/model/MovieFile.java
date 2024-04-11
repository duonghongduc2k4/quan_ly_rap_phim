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
    public double totalCost;
    //    tổng thu
    public double totalRevenue;
    //    đạo diễn
    public String category;
}
