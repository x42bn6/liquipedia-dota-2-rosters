package liquipedia.dota.rosters.scheduling;

public enum Priority {
    HIGHEST(0),
    HIGH(1),
    NORMAL(2),
    LOW(3),
    LOWEST(4);

    private final int value;

    Priority(int value) {
        this.value = value;
    }
}
