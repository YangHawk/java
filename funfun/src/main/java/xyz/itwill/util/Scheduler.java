package xyz.itwill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.itwill.service.FestivalService;

@Component
public class Scheduler {
    private final FestivalService festivalService;

    @Autowired
    public Scheduler(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void autoFestivalStateUpdate() {
    	System.out.println("실행!");
        festivalService.autoModifyFestivalState();
    }
}
