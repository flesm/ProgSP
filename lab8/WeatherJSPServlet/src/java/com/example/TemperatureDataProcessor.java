package com.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.Collectors;

public class TemperatureDataProcessor {
    private List<DayTemperature> dayTemperatures = new ArrayList<>();

    public static class DayTemperature {
        String date;
        double temperature;

        DayTemperature(String date, double temperature) {
            this.date = date;
            this.temperature = temperature;
            
            
        }
        
        public String getDate() {
            return date;
        }

        public double getTemperature() {
            return temperature;
        }
        
    }

    public TemperatureDataProcessor(String filePath) throws Exception {
        loadTemperatureData(filePath);
    }

    private void loadTemperatureData(String filePath) throws Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        NodeList dayList = doc.getElementsByTagName("day");

        System.out.println("Loaded " + dayList.getLength() + " days of weather data.");

        for (int i = 0; i < dayList.getLength(); i++) {
            Node dayNode = dayList.item(i);
            if (dayNode.getNodeType() == Node.ELEMENT_NODE) {
                Element dayElement = (Element) dayNode;
                String date = dayElement.getElementsByTagName("date").item(0).getTextContent();
                String tempStr = dayElement.getElementsByTagName("temperature").item(0).getTextContent();
                System.out.println("Day: " + date + ", Temperature: " + tempStr);  // Debugging output
                dayTemperatures.add(new DayTemperature(date, Double.parseDouble(tempStr)));
            }
        }
    }

    
    public List<DayTemperature> allWeather() {
        return dayTemperatures.stream()
                              .sorted(Comparator.comparingDouble(DayTemperature::getTemperature))
                              .collect(Collectors.toList());
    }   

    public double calculateMonthlyAverage() {
        return dayTemperatures.stream().mapToDouble(d -> d.temperature).average().orElse(0.0);
    }

    public long countAboveAverage(double average) {
        return dayTemperatures.stream().filter(d -> d.temperature > average).count();
    }

    public long countBelowZero() {
        return dayTemperatures.stream().filter(d -> d.temperature < 0).count();
    }

    public String getTopThreeWarmestDays() {
        return dayTemperatures.stream()
                .sorted(Comparator.comparingDouble((DayTemperature d) -> d.temperature).reversed())
                .limit(3)
                .map(d -> d.date)
                .reduce((d1, d2) -> d1 + ", " + d2)
                .orElse("");
    }
    

}
