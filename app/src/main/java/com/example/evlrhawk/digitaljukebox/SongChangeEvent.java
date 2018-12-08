package com.example.evlrhawk.digitaljukebox;

public class SongChangeEvent {
    public final SongRequest request;

    public SongChangeEvent(SongRequest r) {
        this.request = r;
    }
}
