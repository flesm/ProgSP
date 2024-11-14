package weatherapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDataInserter {
    
    public void insertWeatherData(int regionId, String date, double temperature, double precipitation) throws SQLException {
        String query = "INSERT INTO Weather (region_id, date, temperature, precipitation) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, regionId);
                stmt.setString(2, date);
                stmt.setDouble(3, temperature);
                stmt.setDouble(4, precipitation);
                stmt.executeUpdate();
        }
    }
    
    public void updateWeatherData(int weatherId, double temperature, double precipitation) throws SQLException {
        String query = "UPDATE Weather SET temperature = ?, precipitation = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, temperature);
            stmt.setDouble(2, precipitation);
            stmt.setInt(3, weatherId);
            stmt.executeUpdate();
        }
    }

    public void deleteWeatherData(int weatherId) throws SQLException {
        String query = "DELETE FROM Weather WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, weatherId);
            stmt.executeUpdate();
        }
    }
}
