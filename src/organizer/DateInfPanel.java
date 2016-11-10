package organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInfPanel extends JPanel{
    private final int DEFAULT_WIDTH = 510;
    private final int DEFAULT_HEIGHT = 60;

    private Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private JLabel dateLabel = new JLabel("Today: " + dateFormat.format(date));
    private JLabel timeLabel = new JLabel();



    private Timer clockTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date time = Calendar.getInstance().getTime();
            String reportDate = df.format(time);
            timeLabel.setText(reportDate);
            timeLabel.repaint();
        }
    });


    public DateInfPanel() {
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(1,3));
        setBackground(Color.getHSBColor(131,160,125));

        dateLabel.setFont(new Font(null, Font.PLAIN,25));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font(null, Font.BOLD, 35));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        clockTimer.start();

        add(dateLabel);
        add(timeLabel);
    }
}
