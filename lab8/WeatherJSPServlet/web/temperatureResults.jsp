<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Results</title>
    <style>
        body {
            display: flex;
            justify-content: space-between;
        }
        .weather-info {
            width: 50%;
        }
        .results {
            width: 40%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        td:first-child, th:first-child {
            padding-right: 20px; 
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="weather-info">
        <h2>Weather Data</h2>
        <c:if test="${not empty weatherData}">
            <table>
                <tr>
                    <th>Date</th>
                    <th>Temperature (°C)</th>
                </tr>
                <c:forEach var="day" items="${weatherData}">
                    <tr>
                        <td>${day.date}</td>
                        <td>${day.temperature}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty weatherData}">
            <p>No weather data available.</p>
        </c:if>
    </div>
    <div class="results">
        <h2>Results</h2>
        <p>Mid temperature: ${monthlyAverage}°C</p>
        <p>Number of days with temperature higher than mid: ${daysAboveAverage}</p>
        <p>Number of days with temperature below 0°C: ${daysBelowZero}</p>
        <p>The three warmest days: ${topThreeWarmest}</p>
    </div>
</body>
</html>
