package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.shared.dto.UserMovieDto;

import java.util.Collection;

public interface UserMovieService {
    UserMovieDto createAttendee(UserMovieDto userMovieDto);
    long getUserMovieRating(String userId, String scheduleId);
    MovieScheduleDto getUserScheduledMovieByMovieId(String userId, String scheduleId);
    Collection<MovieScheduleDto> getUserBookedMovies(String userId);
}
