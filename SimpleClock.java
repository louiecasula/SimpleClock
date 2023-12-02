//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static java.awt.FlowLayout.CENTER;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout(CENTER, 15, 15)); // Adjust margins
            this.setSize(400, 300);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel = new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel = new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            // Button for 12/24 format
            JToggleButton military = new JToggleButton("12/24");
            military.addItemListener(new MilitaryListener());
            military.setBounds(50,50,150,30);
            military.setFont(new Font("Ink Free",Font.BOLD,20));

            // Button for local time and GMT
            JToggleButton gmt = new JToggleButton("Local/GMT");
            gmt.addItemListener(new GMTListener());
            gmt.setBounds(50,50,150,30);
            gmt.setFont(new Font("Ink Free",Font.BOLD,20));

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(military);
            this.add(gmt);
            this.setVisible(true);

//            setTimer();
            setGMTTimer();
        }
    
        public void setTimer() {
            Thread timer = new Thread(() -> {
                while (true) {
                    time = timeFormat.format(Calendar.getInstance().getTime());
                    timeLabel.setText(time);

                    day = dayFormat.format(Calendar.getInstance().getTime());
                    dayLabel.setText(day);

                    date = dateFormat.format(Calendar.getInstance().getTime());
                    dateLabel.setText(date);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            });
            timer.start();
        }

    public void setGMTTimer() {
        Thread timer = new Thread(() -> {
            while (true) {
                time = timeFormat.format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        });
        timer.start();
    }

        private class MilitaryListener implements ItemListener {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    timeFormat = new SimpleDateFormat("HH:mm:ss");
                }
                else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                }
            }
        }

    private class GMTListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
//            if (e.getStateChange() == ItemEvent.SELECTED){
//                setGMTTimer();
//            }
//            else {
//                setTimer();
//            }
        }
    }

        public static void main(String[] args) {
            new SimpleClock();
        }
}
