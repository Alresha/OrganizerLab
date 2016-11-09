package organizer;

import organizer.model.DayCollection;
import organizer.model.NoteModel;
import organizer.model.NoteTimeModel;
import organizer.model.TimeTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Katerina on 24.10.2016.
 */
public class AddNoteDialog extends CommonDialog {

    public AddNoteDialog(final JFrame owner,  DayCollection model, KeyDateObject key, String dayTime, TablePanel tablePanel){
        super(owner, model, "Add Note");

    okButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            NoteModel note = new NoteModel();
            note.setNote(noteArea.getText());
            NoteTimeModel newNote = new NoteTimeModel(note, dayTime);

            if (model.containsKey(key)) {
                model.get(key).addNoteTime(newNote);
            } else {
                TimeTableModel newTable = new TimeTableModel();
                model.put(key, newTable);
                newTable.addNoteTime(newNote);
            }
            setVisible(false);
            tablePanel.updateTablePanel(key, model);
        }
    }

    );
}
    public JButton getOkButton(){return this.okButton;}

}