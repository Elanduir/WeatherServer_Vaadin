package com.example.WeatherServer;

import java.sql.Date;

public class WeatherData {
    final Location loc;
    final double temp;
    final double humidity;
    final double pressure;
    final Date date;
    public WeatherData(Location loc, Date date, double temp, double humidity, double pressure){
        this.loc = loc;
        this.date = date;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
