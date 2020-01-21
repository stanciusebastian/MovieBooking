package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.MovieScheduleDto;

import java.util.List;

public interface MovieScheduleService {
    List<MovieScheduleDto> getMoviesSchedules();
    MovieScheduleDto createSchedule(MovieScheduleDto movieScheduleDto);
    MovieScheduleDto getMovieScheduleByScheduleId(String scheduleId);
    MovieScheduleDto updateSchedule(String scheduleId, MovieScheduleDto movieScheduleDto);
    void deleteSchedule(String scheduleId);
}
