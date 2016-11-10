package organizer.dialogs;

import organizer.model.DayCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommonDialog extends JDialog {
    protected final int DEFAULT_WIDTH = 300;
    protected final int DEFAULT_HEIGHT = 130;

    protected DayCollection modelCollection;

    protected JTextField noteArea = new JTextField(33);
    protected JButton okButton = new JButton("OK");
    protected JButton cancelButton = new JButton("CANCEL");

    JPanel notePanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    public CommonDialog(final JFrame owner, DayCollection model, String name) {
        super(owner, name, true);
        this.modelCollection = model;
        setLayout(new GridLayout(2,1));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        notePanel.add(noteArea);
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        add(notePanel);
        add(buttonsPanel);

        setLocationRelativeTo(null);

        setResizable(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}

