package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import com.example.TemperatureDataProcessor;

public class TemperatureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String filePath = "D:\\Works\\progsp\\lab8\\WeatherJSPServlet\\web\\WEB-INF\\temperature_data.xml";
            TemperatureDataProcessor processor = new TemperatureDataProcessor(filePath);
            
            double monthlyAverage = processor.calculateMonthlyAverage();
            long daysAboveAverage = processor.countAboveAverage(monthlyAverage);
            long daysBelowZero = processor.countBelowZero();
            String topThreeWarmest = processor.getTopThreeWarmestDays();
            List<TemperatureDataProcessor.DayTemperature> all = processor.allWeather();
            System.out.println("Weather data size: " + all.size());

            
            request.setAttribute("monthlyAverage", monthlyAverage);
            request.setAttribute("daysAboveAverage", daysAboveAverage);
            request.setAttribute("daysBelowZero", daysBelowZero);
            request.setAttribute("topThreeWarmest", topThreeWarmest);
            request.setAttribute("weatherData", all);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("temperatureResults.jsp");
            
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TemperatureServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    