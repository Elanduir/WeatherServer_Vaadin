package com.example.WeatherServer;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
    public MainView(){
        SQLUtility sql = new SQLUtility();
        WeatherData wohlenWD = sql.getLatestCacheByLocation(Location.WOHLEN);
        Paragraph locationW = new Paragraph(wohlenWD.loc.name());
        Paragraph tempW = new Paragraph(String.valueOf(wohlenWD.temp));
        Paragraph humidityW = new Paragraph(String.valueOf(wohlenWD.humidity));
        Paragraph pressureW = new Paragraph(String.valueOf(wohlenWD.pressure));

        WeatherData LenzburgWD = sql.getLatestCacheByLocation(Location.LENZBURG);
        Paragraph locationL = new Paragraph(LenzburgWD.loc.name());
        Paragraph tempL = new Paragraph(String.valueOf(LenzburgWD.temp));
        Paragraph humidityL = new Paragraph(String.valueOf(LenzburgWD.humidity));
        Paragraph pressureL = new Paragraph(String.valueOf(LenzburgWD.pressure));

        List<OverviewData> overview = sql.getStationOverview();
        Grid<OverviewData> weatherGrid = new Grid<>();
        weatherGrid.setItems(overview);
        weatherGrid.addColumn(OverviewData::getDate).setHeader("Date");
        weatherGrid.addColumn(OverviewData::getTempWohlen).setHeader("Temp Wohlen");
        weatherGrid.addColumn(OverviewData::getHumidityWohlen).setHeader("Humidity Wohlen");
        weatherGrid.addColumn(OverviewData::getPressureWohlen).setHeader("Pressure Wohlen");
        weatherGrid.addColumn(OverviewData::getTempLenzburg).setHeader("Temp Lenzburg");
        weatherGrid.addColumn(OverviewData::getHumidityLenzburg).setHeader("Humidity Lenzburg");
        weatherGrid.addColumn(OverviewData::getPressureLenzburg).setHeader("Pressure Lenzburg");





        add(locationW, new HorizontalLayout(tempW, humidityW, pressureW));
        add(locationL, new HorizontalLayout(tempL, humidityL, pressureL));
        add(weatherGrid);

        sql.close();

    }
}
