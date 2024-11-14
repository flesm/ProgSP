<%@page import="weatherapp.WeatherQuery"%>
<%@page import="weatherapp.WeatherDataInserter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Information</title>
</head>
<body>
    <h2>Weather Information</h2>

    <!-- Form to Display All Weather Data -->
    <form action="index.jsp" method="GET">
        <!-- Hidden field to pass the action parameter -->
        <input type="hidden" name="action" value="Get All Weather Data">
        <input type="submit" value="Get All Weather Data">
    </form>
    
    <!-- Form to Filter Weather Data by Region, Temperature, Area, and Language -->
    <h3>Filter Weather Data</h3>
    <form action="index.jsp" method="GET">
        <label>Region Name: </label>
        <input type="text" name="filterRegionName"><br>
        <label>Temperature Threshold: </label>
        <input type="text" name="temperatureThreshold"><br>
        <label>Area Threshold: </label>
        <input type="text" name="areaThreshold"><br>
        <label>Language: </label>
        <input type="text" name="language"><br>
        <input type="submit" value="Filter Weather Data">
    </form>
    
    <!-- Form to Add Weather Data -->
    <h3>Add Weather Data</h3>
    <form action="index.jsp" method="POST">
        <input type="hidden" name="action" value="Add Weather">
        <label>Region ID: </label>
        <input type="text" name="addRegionId"><br>
        <label>Date (YYYY-MM-DD): </label>
        <input type="text" name="addDate"><br>
        <label>Temperature: </label>
        <input type="text" name="addTemperature"><br>
        <label>Precipitation: </label>
        <input type="text" name="addPrecipitation"><br>
        <input type="submit" value="Add Weather">
    </form>
    
    <h2>Update Weather Data</h2>
    <form action="index.jsp" method="POST">
        <input type="hidden" name="action" value="Update Weather">
        <label>Weather ID:</label><input type="text" name="weatherId"><br>
        <label>New Temperature:</label><input type="text" name="temperature"><br>
        <label>New Precipitation:</label><input type="text" name="precipitation"><br>
        <input type="submit" value="Update">
    </form>
    
    <h2>Delete Weather Data</h2>
    <form action="index.jsp" method="POST">
        <input type="hidden" name="action" value="Delete Weather">
        <label>Weather ID:</label><input type="text" name="weatherId"><br>
        <input type="submit" value="Delete">
    </form>

    <%
        // Initialize query and action parameters
        WeatherQuery weatherQuery = new WeatherQuery();
        WeatherDataInserter inserter = new WeatherDataInserter();
        
        String action = request.getParameter("action");
        
        // Handle Add Weather action
        if ("Add Weather".equals(action)) {
            try {
                int regionId = Integer.parseInt(request.getParameter("addRegionId"));
                String date = request.getParameter("addDate");
                double temperature = Double.parseDouble(request.getParameter("addTemperature"));
                double precipitation = Double.parseDouble(request.getParameter("addPrecipitation"));
                
                inserter.insertWeatherData(regionId, date, temperature, precipitation);
                out.println("<p>Weather data added successfully!</p>");
            } catch (SQLException | NumberFormatException e) {
                out.println("<p>Error adding weather data: " + e.getMessage() + "</p>");
            }
        }

        // Handle Update Weather action
        else if ("Update Weather".equals(action)) {
            try {
                int weatherId = Integer.parseInt(request.getParameter("weatherId"));
                double temperature = Double.parseDouble(request.getParameter("temperature"));
                double precipitation = Double.parseDouble(request.getParameter("precipitation"));
                
                inserter.updateWeatherData(weatherId, temperature, precipitation);
                out.println("<p>Weather data updated successfully!</p>");
            } catch (SQLException | NumberFormatException e) {
                out.println("<p>Error updating weather data: " + e.getMessage() + "</p>");
            }
        }

        // Handle Delete Weather action
        else if ("Delete Weather".equals(action)) {
            try {
                int weatherId = Integer.parseInt(request.getParameter("weatherId"));
                
                inserter.deleteWeatherData(weatherId);
                out.println("<p>Weather data deleted successfully!</p>");
            } catch (SQLException | NumberFormatException e) {
                out.println("<p>Error deleting weather data: " + e.getMessage() + "</p>");
            }
        }

        // Handle Display All Weather
        if (action != null && action.equals("Get All Weather Data")) {
            out.println("<h3>All Weather Data:</h3>");
            try {
                // Get all weather data from the database
                List<String> weatherInfo = weatherQuery.getAllWeatherData();
                for (String info : weatherInfo) {
                    out.println("<p>" + info + "</p>");
                }
            } catch (SQLException e) {
                out.println("<p>Error retrieving weather data: " + e.getMessage() + "</p>");
            }
        }
        
        String filterRegionName = request.getParameter("filterRegionName");
        String temperatureThresholdStr = request.getParameter("temperatureThreshold");
        String areaThresholdStr = request.getParameter("areaThreshold");
        String language = request.getParameter("language");

        if (filterRegionName != null && !filterRegionName.isEmpty() && temperatureThresholdStr != null && !temperatureThresholdStr.isEmpty()
                && areaThresholdStr != null && !areaThresholdStr.isEmpty() && language != null && !language.isEmpty()) {
            out.println("<h3>Filtered Weather Data:</h3>");
            try {
                double temperatureThreshold = Double.parseDouble(temperatureThresholdStr);
                double areaThreshold = Double.parseDouble(areaThresholdStr);

                List<String> snowDates = weatherQuery.getSnowDatesWithLowTemp(filterRegionName, temperatureThreshold);
                double avgTemperature = weatherQuery.getAverageTemperatureLastWeek(areaThreshold);
                List<String> weatherByLanguage = weatherQuery.getWeatherByLanguageLastWeek(language);
                List<String> weatherByRegion = weatherQuery.getWeatherByRegion(filterRegionName);

                out.println("<h4>Weather by region:</h4>");
                for (String weather : weatherByRegion) {
                    out.println("<p>" + weather + "</p>");
                }
                
                out.println("<h4>Snow Dates with Low Temperature:</h4>");
                for (String date : snowDates) {
                    out.println("<p>" + date + "</p>");
                }

                out.println("<h4>Average Temperature Last Week (Regions with Area > " + areaThreshold + "):</h4>");
                out.println("<p>Average Temperature: " + avgTemperature + "°C</p>");

                out.println("<h4>Weather Data from Last Week for Regions with Language: " + language + "</h4>");
                for (String info : weatherByLanguage) {
                    out.println("<p>" + info + "</p>");
                }

            } catch (SQLException e) {
                out.println("<p>Error retrieving filtered weather data: " + e.getMessage() + "</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input for temperature or area thresholds. Please enter valid numbers.</p>");
            }
        }
    %>
</body>
</html>
