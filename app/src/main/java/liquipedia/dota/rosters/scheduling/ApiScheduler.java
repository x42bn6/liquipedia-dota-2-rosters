package liquipedia.dota.rosters.scheduling;

import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ApiScheduler {
    private final int delaySeconds;

    private static final Logger logger = getLogger(ApiScheduler.class);
    private final Queue<TaskWithPriority<?>> queue = new PriorityBlockingQueue<>(10);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public ApiScheduler(int delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public void start() {
        scheduledExecutorService.scheduleWithFixedDelay(this::processHead, 0, delaySeconds, TimeUnit.SECONDS);
    }

    public void schedule(Runnable task, Priority priority) {
        queue.add(new TaskWithPriority<>(task, priority, System.nanoTime()));
    }

    private void processHead() {
        try {
            TaskWithPriority<?> poll = queue.poll();
            if (null == poll) {
                return;
            }

            poll.task().run();
        } catch (RuntimeException e) {
            logger.error("Exception while processing head", e);
        }
    }
}
