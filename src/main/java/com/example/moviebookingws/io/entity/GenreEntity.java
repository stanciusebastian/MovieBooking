package com.example.moviebookingws.io.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Genre")
public class GenreEntity {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable =  false, length = 35)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<MovieEntity> movies;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies = movies;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
