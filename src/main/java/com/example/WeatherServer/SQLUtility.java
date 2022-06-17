package com.example.WeatherServer;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SQLUtility {
    Connection db;
    Statement stmt;
    final String PATTERN = "MM.dd.yyyy HH:mm";
    public SQLUtility(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.db = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/db_weather", "root", "wasser1");
            stmt = db.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public WeatherData getLatestCacheByLocation(Location loc){
        String statement = "SELECT * FROM " + loc.toString() + "_cache order by date_created desc limit 1";
        System.out.println(statement);
        try{
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            return new WeatherData(loc, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<OverviewData> getStationOverview(){
        List<OverviewData> overview = new ArrayList<>();

        String statement = "SELECT * FROM v_weatherStationOverview";
        WeatherData wW;
        WeatherData wL;
        try{
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()){
                wW = new WeatherData(Location.WOHLEN, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
                wL = new WeatherData(Location.LENZBURG, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
                OverviewData o = new OverviewData(wW, wL);
                overview.add(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return overview;
    }

    public void close(){
        try{
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
