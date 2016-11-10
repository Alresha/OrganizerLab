package organizer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CalendarInfPanel extends JPanel {

    private final int DEFAULT_WIDTH = 300;
    private final int DEFAULT_HEIGHT = 500;

    public CalendarInfPanel(){
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new BorderLayout());

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        }
}
