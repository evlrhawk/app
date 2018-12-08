package com.example.evlrhawk.digitaljukebox;

public class SongRemoveEvent {
    public final SongRequest request;

    public SongRemoveEvent(SongRequest request) {
        this.request = request;
    }
}
