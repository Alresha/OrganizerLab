package organizer;

import organizer.model.DayCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Katerina on 27.09.2016.
 */
public class ButtonPanel extends JPanel{

    private final int DEFAULT_WIDTH = 90;
    private final int DEFAULT_HEIGHT = 500;

    private DayCollection modelCollection;

   // private JButton newNoteButton = new JButton("New");
   // private JButton editNoteButton = new JButton("Edit");
   // private JButton deleteNoteButton = new JButton("Delete");

    public ButtonPanel(DayCollection model) {
        this.modelCollection = model;

        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBorder(BorderFactory.createLoweredBevelBorder());

    }

}
