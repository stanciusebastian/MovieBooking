package com.example.moviebookingws.scheduler;

import com.example.moviebookingws.io.entity.MovieScheduleEntity;
import com.example.moviebookingws.io.entity.UserMovieEntity;
import com.example.moviebookingws.io.repositories.MovieScheduleRepository;
import com.example.moviebookingws.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MovieReminderScheduler {

    @Autowired
    private MovieScheduleRepository movieScheduleRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *")
    public void run() {
        if (!movieScheduleRepository.getScheduledMoviesAfterAnHour().isEmpty()) {
            for (MovieScheduleEntity movieScheduleEntity : movieScheduleRepository.getScheduledMoviesAfterAnHour()) {
                for (UserMovieEntity userMovieEntity : movieScheduleEntity.getUsersJoined()) {
                    String userEmail = userMovieEntity.getUser().getEmail();
                    MovieScheduleEntity movieSchedule = userMovieEntity.getMovieSchedule();
                    String movieName = movieSchedule.getMovieSchedule().getName();
                    Date movieHour = movieSchedule.getSchedule();
                    String movieHall = movieSchedule.getHall();
                    String emailBody = "The movie " + movieName + " will start in a hour at " + movieHour + " in the hall " + movieHall;
                    emailService.sendSimpleMessage(userEmail, "Movie Reminder", emailBody);
                    System.out.println(emailBody + " "  + "for the user " + userMovieEntity.getUser().getFullName());
                }
            }
        }
    }
}
