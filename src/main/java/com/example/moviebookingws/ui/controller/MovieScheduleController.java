package com.example.moviebookingws.ui.controller;


import com.example.moviebookingws.service.MovieScheduleService;
import com.example.moviebookingws.shared.dto.MovieScheduleDto;
import com.example.moviebookingws.ui.model.request.MovieScheduleDetailsRequestModel;
import com.example.moviebookingws.ui.model.response.MovieScheduleRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies-schedule") //http://localhost::8080/movies-schedule
public class MovieScheduleController {
    @Autowired
    private MovieScheduleService movieScheduleService;

    @GetMapping("/{scheduleId}")
    public MovieScheduleRest getMovieSchedule(@PathVariable String scheduleId) {
        MovieScheduleDto movieScheduleDto = movieScheduleService.getMovieScheduleByScheduleId(scheduleId);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDto, movieScheduleRest);
        return movieScheduleRest;
    }

    @PostMapping
    public MovieScheduleRest createMovieSchedule(@RequestBody MovieScheduleDetailsRequestModel movieScheduleDetailsRequestModel){
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        BeanUtils.copyProperties(movieScheduleDetailsRequestModel,movieScheduleDto);
        MovieScheduleDto movieScheduleDto1 = movieScheduleService.createSchedule(movieScheduleDto);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(movieScheduleDto1,movieScheduleRest);
        return movieScheduleRest;
    }

    @PutMapping("/{scheduleId}")
    public MovieScheduleRest updateMovieSchedule(@PathVariable String scheduleId, @RequestBody MovieScheduleDetailsRequestModel movieScheduleDetailsRequestModel){
        MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
        BeanUtils.copyProperties(movieScheduleDetailsRequestModel, movieScheduleDto);
        MovieScheduleDto scheduleDto  = movieScheduleService.updateSchedule(scheduleId, movieScheduleDto);
        MovieScheduleRest movieScheduleRest = new MovieScheduleRest();
        BeanUtils.copyProperties(scheduleDto, movieScheduleRest);
        return movieScheduleRest;
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable String scheduleId){
        movieScheduleService.deleteSchedule(scheduleId);
    }
}
