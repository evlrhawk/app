package com.example.evlrhawk.digitaljukebox;

public class ToSend {
    private String toSend;

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public ToSend(String toSend){
        this.toSend = toSend;
    }

    public ToSend(){ toSend = ""; }
}
