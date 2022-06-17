package com.example.WeatherServer;

import java.sql.*;

public class SQLUtility {
    Connection db;
    Statement stmt;
    public SQLUtility(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.db = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/db_weather", "root", "wasser1");
            stmt = db.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public WeatherData getLatestCache(Location loc){
        String statement = "SELECT * FROM " + loc.toString() + "_cache order by date_created desc limit 1";
        System.out.println(statement);
        try{
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            return new WeatherData(loc, rs.getDate(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
        try{
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
