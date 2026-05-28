package liquipedia.dota.rosters.scheduling;

import java.util.Comparator;
import java.util.function.Function;

public record TaskWithPriority<T>(Runnable task,
                                  Priority priority,
                                  long nanos) implements Comparable<TaskWithPriority<T>> {
    @Override
    public int compareTo(TaskWithPriority<T> o) {
        return Comparator.comparing(TaskWithPriority<T>::priority)
                .thenComparing(TaskWithPriority::nanos)
                .compare(this, o);
    }
}
