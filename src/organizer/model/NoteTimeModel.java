package organizer.model;

import organizer.model.NoteModel;

/**
 * Created by Katerina on 20.09.2016.
 */
public class NoteTimeModel {

    private NoteModel noteModel;
    private String dayTime;

    public String getDayTime() {return this.dayTime;}
    public void setDayTime(String newDayTime) {this.dayTime=newDayTime;}

    public String getNoteString() {return this.noteModel.getNote();}
    public void setNoteString(String newNote) {this.noteModel.setNote(newNote);}

    public NoteTimeModel(NoteModel note, String dayTime)
    {
        this.noteModel = note;
        this.dayTime = dayTime;
    }
}
