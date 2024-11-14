package weatherapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherQuery {
    public List<String> getWeatherByRegion(String regionName) throws SQLException {
        String query = "SELECT date, temperature, precipitation FROM Weather W "
                     + "JOIN Region R ON W.region_id = R.id WHERE R.name = ?";
        List<String> results = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, regionName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add("Date: " + rs.getDate("date") + ", Temp: " + rs.getDouble("temperature") +
                            ", Precipitation: " + rs.getDouble("precipitation"));
            }
        }
        return results;
    }

    public List<String> getSnowDatesWithLowTemp(String regionName, double temperatureThreshold) throws SQLException {
        String query = "SELECT date FROM Weather W "
                     + "JOIN Region R ON W.region_id = R.id "
                     + "WHERE R.name = ? AND W.temperature < ? AND W.precipitation > 0";
        List<String> results = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, regionName);
            stmt.setDouble(2, temperatureThreshold);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add("Date: " + rs.getDate("date"));
            }
        }
        return results;
    }

    public List<String> getWeatherByLanguageLastWeek(String language) throws SQLException {
        String query = "SELECT R.name, W.date, W.temperature, W.precipitation FROM Weather W "
                     + "JOIN Region R ON W.region_id = R.id "
                     + "JOIN ResidentType RT ON R.resident_type_id = RT.id "
                     + "WHERE RT.communication_language = ? AND W.date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        List<String> results = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add("Region: " + rs.getString("name") + ", Date: " + rs.getDate("date") +
                            ", Temp: " + rs.getDouble("temperature") + ", Precipitation: " + rs.getDouble("precipitation"));
            }
        }
        return results;
    }

    public double getAverageTemperatureLastWeek(double areaThreshold) throws SQLException {
        String query = "SELECT AVG(W.temperature) as avgTemp FROM Weather W "
                     + "JOIN Region R ON W.region_id = R.id "
                     + "WHERE R.area > ? AND W.date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        double avgTemp = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, areaThreshold);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                avgTemp = rs.getDouble("avgTemp");
            }
        }
        return avgTemp;
    }
    
    
    public List<String> getAllWeatherData() throws SQLException {
        List<String> results = new ArrayList<>();
        String query = "SELECT Region.name, Weather.id,Weather.date, Weather.temperature, Weather.precipitation " +
                       "FROM Weather " +
                       "JOIN Region ON Weather.region_id = Region.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                results.add(rs.getString("id") + " Region: " + rs.getString("name") + ", Date: " + rs.getString("date") +
                            ", Temperature: " + rs.getDouble("temperature") + ", Precipitation: " + rs.getDouble("precipitation"));
            }
        }
        return results;
    }
}
