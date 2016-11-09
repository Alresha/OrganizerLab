package organizer;

import organizer.model.DayCollection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Katerina on 24.10.2016.
 */
public class AddNoteListener implements ActionListener {
    private DayCollection modelCollection;
    private KeyDateObject keyDate;
    private String dayTime;
    private TablePanel tablePanel;

    public AddNoteListener( DayCollection model, KeyDateObject key, String dayTime, TablePanel tablePanel) {
        this.modelCollection = model;
        this.keyDate = key;
        this.dayTime = dayTime;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddNoteDialog newEntryDialog = new AddNoteDialog(null,  modelCollection, keyDate, dayTime, tablePanel);
        newEntryDialog.setVisible(true);
    }
}