package com.example.moviebookingws.io.entity;


import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Movie")
public class MovieEntity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable = false, length = 35)
    private String name;

    @OneToMany(mappedBy = "movie")
    private Set<UserMovieEntity> usersJoined;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    Set<ActorEntity> playedActors;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;

    public Set<UserMovieEntity> getUsersJoined() {
        return usersJoined;
    }

    public void setUsersJoined(Set<UserMovieEntity> usersJoined) {
        this.usersJoined = usersJoined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
