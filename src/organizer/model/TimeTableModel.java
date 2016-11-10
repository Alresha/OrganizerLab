package organizer.model;

import organizer.KeyDateObject;
import organizer.model.NoteTimeModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katerina on 20.09.2016.
 */
public class TimeTableModel extends AbstractTableModel {

    private final int DAYTIME_COLUMN = 0;
    private final int NOTES_COLUMN = 1;
    private KeyDateObject keyDateObject;

    private String[] dayTimeNames = {"7-9", "9-11", "11-13", "13-15", "15-17", "17-19", "19-21"};
    private List<NoteTimeModel> notesPack = new ArrayList<NoteTimeModel>();

    public TimeTableModel() {
    }

    @Override
    public int getRowCount() {
        return 7;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }


    @Override
    public String getValueAt(int colIndex, int rowIndex) {
        switch (colIndex) {
            case DAYTIME_COLUMN:
                return dayTimeNames[rowIndex];
            case NOTES_COLUMN:
                    for (int packIndex=0; packIndex<notesPack.size(); packIndex++)
                    {
                        if(dayTimeNames[rowIndex].equals(notesPack.get(packIndex).getDayTime()))
                        return notesPack.get(packIndex).getNoteString();}
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public List<NoteTimeModel> getNotesPack() {
        return notesPack;
    }

    public void addNoteTime(NoteTimeModel newNote) {

        Boolean itemWasFound = false;
        for (int notePackIndex=0; notePackIndex< notesPack.size(); notePackIndex++)
        {
            if (notesPack.get(notePackIndex).getDayTime().equals(newNote.getDayTime()))
            {
                notesPack.get(notePackIndex).setNoteString(notesPack.get(notePackIndex).getNoteString()+"; "
                        + newNote.getNoteString());
                itemWasFound = true;
            }
        }
        if (!itemWasFound) notesPack.add(newNote);
    }

    public void replaceNoteTime(NoteTimeModel newNote)
    {
        for (int notePackIndex=0; notePackIndex< notesPack.size(); notePackIndex++)
        {
            if (notesPack.get(notePackIndex).getDayTime().equals(newNote.getDayTime()))
            {
                notesPack.remove(notePackIndex);
                notesPack.add(newNote);
            }
        }

    }

    public void removeNoteTime(NoteTimeModel deletedNote)
    {
        for (int notePackIndex=0; notePackIndex< notesPack.size(); notePackIndex++)
        {
            if (notesPack.get(notePackIndex).getDayTime().equals(deletedNote.getDayTime()))
            {
                notesPack.remove(notePackIndex);
            }
        }
    }


    public int getRowNumber (String dayTime) {
        int returnedValue = 0;
        for (int rowIndex = 0; rowIndex < 7; rowIndex++) {
            if (dayTime.equals(dayTimeNames[rowIndex]))
                returnedValue = rowIndex;
        }
        return returnedValue;
    }

    public void setKeyDate(KeyDateObject key){
        this.keyDateObject = key;
    }

    public KeyDateObject getKeyDateObject(){
        return this.keyDateObject;
    }
}