package com.example.moviebookingws.io.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Movies")
public class MovieEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable =  false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;


}
