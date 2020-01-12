package com.example.moviebookingws.service;

import com.example.moviebookingws.shared.dto.MovieScheduleDto;

public interface MovieScheduleService {
    MovieScheduleDto createSchedule(MovieScheduleDto movieScheduleDto);
    MovieScheduleDto getMovieScheduleByScheduleId(String scheduleId);
    MovieScheduleDto updateSchedule(String scheduleId, MovieScheduleDto movieScheduleDto);
    void deleteSchedule(String scheduleId);
}
