package com.lulugyda.jobs;

import com.lulugyda.models.entities.UserEntity;
import com.lulugyda.models.responses.MovieListResponse;
import com.lulugyda.repositories.UsersCrudRepository;
import com.lulugyda.services.EmailService;
import com.lulugyda.services.MovieService;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

import static com.lulugyda.utils.Helper.convertToHtml;

@Slf4j
@Setter
@Singleton
@AllArgsConstructor
public class EmailJob {

    private final EmailService emailService;
    private final UsersCrudRepository usersCrudRepository;
    private final MovieService movieService;

    // (cron) at 12:00 every Sunday = 0 12 * * 0
    @Scheduled(cron = "0 12 * * 0", zoneId = "Asia/Riyadh")
    public void sendWeeklyEmailJob() {
     MovieListResponse response = movieService.getMovieList("1");
      List<UserEntity> users = usersCrudRepository.findAll();

        for (UserEntity var : users) {
            emailService.sendEmail(var.getEmail(), "Weekly list of movies", convertToHtml(response));
        }

        log.info("emails sent at:" + new Date().getTime());

    }
}
