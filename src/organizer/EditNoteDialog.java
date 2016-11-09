package organizer;

import organizer.model.DayCollection;
import organizer.model.NoteModel;
import organizer.model.NoteTimeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Katerina on 08.11.2016.
 */
public class EditNoteDialog extends CommonDialog {

    public EditNoteDialog(final JFrame owner, DayCollection model, String prevNote, KeyDateObject key, String dayTime, TablePanel tablePanel) {
        super(owner, model, "Edit Note");

        noteArea.setText(prevNote);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoteModel note = new NoteModel();
                note.setNote(noteArea.getText());
                NoteTimeModel newNote = new NoteTimeModel(note, dayTime);

                model.get(key).replaceNoteTime(newNote);
                setVisible(false);
                tablePanel.updateTablePanel(key, model);
            }
        });
    }
}