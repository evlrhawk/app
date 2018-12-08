package com.example.evlrhawk.digitaljukebox;

public class SongPausePlayEvent {
    public final boolean paused;
    public final long timeRemaining;

    public SongPausePlayEvent(boolean paused, long timeRemaining) {
        this.paused = paused;
        this.timeRemaining = timeRemaining;
    }
}
