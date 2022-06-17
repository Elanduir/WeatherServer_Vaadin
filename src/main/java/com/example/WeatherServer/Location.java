package com.example.WeatherServer;

public enum Location{
    WOHLEN("5610_wohlen"),
    LENZBURG("5600_lenzburg");

    private final String name;
    private Location(String s){
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
