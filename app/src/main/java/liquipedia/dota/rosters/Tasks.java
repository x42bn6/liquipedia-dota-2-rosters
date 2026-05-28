package liquipedia.dota.rosters;

import liquipedia.dota.rosters.scheduling.ApiScheduler;
import liquipedia.dota.rosters.scheduling.Priority;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Tasks {
    private final ApiScheduler faceitApiScheduler;

    public Tasks(ApiScheduler faceitApiScheduler) {
        this.faceitApiScheduler = faceitApiScheduler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        faceitApiScheduler.start();
        faceitApiScheduler.schedule(() -> System.out.println("NORMAL"), Priority.NORMAL);
        faceitApiScheduler.schedule(() -> System.out.println("HIGH"), Priority.HIGH);
        faceitApiScheduler.schedule(() -> System.out.println("LOW"), Priority.LOW);
    }
}
