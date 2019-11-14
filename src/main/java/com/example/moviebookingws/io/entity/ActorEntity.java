package com.example.moviebookingws.io.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Actor")
public class ActorEntity {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable =  false, length = 35)
    private String firstName;

    @Column(nullable =  false, length = 35)
    private String lastName;

    @Column(nullable = true, length = 10)
    private long age;

    @Column(nullable = true, length = 10)
    private String gender;

    @ManyToMany(mappedBy = "playedActors")
    private Set<MovieEntity> playedMovies;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<MovieEntity> getPlayedMovies() {
        return playedMovies;
    }

    public void setPlayedMovies(Set<MovieEntity> playedMovies) {
        this.playedMovies = playedMovies;
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
