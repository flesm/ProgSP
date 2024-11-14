import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

class ClockPanel extends JPanel {
    private LocalTime currentTime;

    public ClockPanel() {
        currentTime = LocalTime.now();
    }

    public void updateTime() {
        currentTime = LocalTime.now();
        repaint();
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        double secondAngle = Math.toRadians((currentTime.getSecond() / 60.0) * 360 - 90);
        double minuteAngle = Math.toRadians((currentTime.getMinute() / 60.0) * 360 - 90);
        double hourAngle = Math.toRadians(((currentTime.getHour() % 12) / 12.0) * 360 - 90);

        int secondX = centerX + (int) (radius * 0.9 * Math.cos(secondAngle));
        int secondY = centerY + (int) (radius * 0.9 * Math.sin(secondAngle));

        int minuteX = centerX + (int) (radius * 0.7 * Math.cos(minuteAngle));
        int minuteY = centerY + (int) (radius * 0.7 * Math.sin(minuteAngle));

        int hourX = centerX + (int) (radius * 0.5 * Math.cos(hourAngle));
        int hourY = centerY + (int) (radius * 0.5 * Math.sin(hourAngle));

        g.drawLine(centerX, centerY, secondX, secondY);
        g.drawLine(centerX, centerY, minuteX, minuteY);
        g.drawLine(centerX, centerY, hourX, hourY);
    }
}