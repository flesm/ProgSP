import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class ClockApp extends JFrame {
    private ClockPanel clockPanel;
    private Thread timeUpdateThread;
    private Thread alarmCheckThread;
    private boolean running = false;
    private LocalTime alarmTime;

    public ClockApp() {
        setTitle("Гадзіннік з будзільнікам");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clockPanel = new ClockPanel();
        add(clockPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Старт");
        JButton stopButton = new JButton("Стоп");
        JButton setAlarmButton = new JButton("Паставіць будзільнік");

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(setAlarmButton);

        add(controlPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startClock();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopClock();
            }
        });

        setAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAlarm();
            }
        });
    }

    private void startClock() {
        if (!running) {
            running = true;

            timeUpdateThread = new Thread(new TimeUpdateTask());
            alarmCheckThread = new Thread(new AlarmCheckTask());

            timeUpdateThread.start();
            alarmCheckThread.start();
        }
    }

    private void stopClock() {
        running = false;
        try {
            if (timeUpdateThread != null) timeUpdateThread.join();
            if (alarmCheckThread != null) alarmCheckThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setAlarm() {
        String input = JOptionPane.showInputDialog(this, "Увядзіце час будзільніка (гг:хх:сс):");
        alarmTime = LocalTime.parse(input);
    }

    private class TimeUpdateTask implements Runnable {
        @Override
        public void run() {
            while (running) {
                clockPanel.updateTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AlarmCheckTask implements Runnable {
        @Override
        public void run() {
            while (running) {
                if (alarmTime != null) {
                    LocalTime currentTime = clockPanel.getCurrentTime();
                    if (currentTime.getHour() == alarmTime.getHour() &&
                            currentTime.getMinute() == alarmTime.getMinute() &&
                            currentTime.getSecond() == alarmTime.getSecond()) {
                        JOptionPane.showMessageDialog(ClockApp.this, "Будзільнік у " + alarmTime + " спрацаваў!");
                        alarmTime = null;
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClockApp app = new ClockApp();
            app.setVisible(true);
        });
    }
}