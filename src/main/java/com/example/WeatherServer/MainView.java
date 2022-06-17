package com.example.WeatherServer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    public MainView(){
        SQLUtility sql = new SQLUtility();
        WeatherData wohlenWD = sql.getLatestCache(Location.WOHLEN);
        Paragraph locationW = new Paragraph(wohlenWD.loc.name());
        Paragraph tempW = new Paragraph(String.valueOf(wohlenWD.temp));
        Paragraph humidityW = new Paragraph(String.valueOf(wohlenWD.humidity));
        Paragraph pressureW = new Paragraph(String.valueOf(wohlenWD.pressure));

        WeatherData LenzburgWD = sql.getLatestCache(Location.LENZBURG);
        Paragraph locationL = new Paragraph(LenzburgWD.loc.name());
        Paragraph tempL = new Paragraph(String.valueOf(LenzburgWD.temp));
        Paragraph humidityL = new Paragraph(String.valueOf(LenzburgWD.humidity));
        Paragraph pressureL = new Paragraph(String.valueOf(LenzburgWD.pressure));

        add(locationW, new HorizontalLayout(tempW, humidityW, pressureW));
        add(locationL, new HorizontalLayout(tempL, humidityL, pressureL));

        sql.close();

    }
}
