package organizer;

import organizer.model.DayCollection;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{

    private final int DEFAULT_WIDTH = 90;
    private final int DEFAULT_HEIGHT = 500;

    private DayCollection modelCollection;

    public ButtonPanel(DayCollection model) {
        this.modelCollection = model;

        setLayout(new GridLayout(5,1));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBorder(BorderFactory.createLoweredBevelBorder());

    }

}
