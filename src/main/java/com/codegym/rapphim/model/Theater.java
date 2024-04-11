package com.codegym.rapphim.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    id rạp phim
    private int id;
//    tên rạp phim
    private String nameTheater;
//    Vị trí
    private String location;



}
