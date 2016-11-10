package organizer.filelistener;

import java.awt.event.ActionListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import organizer.KeyDateObject;
import organizer.model.DayCollection;
import organizer.model.NoteModel;
import organizer.model.NoteTimeModel;
import organizer.model.TimeTableModel;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenFileListener implements ActionListener{
    private DayCollection modelCollection;

    public OpenFileListener(DayCollection model){
        this.modelCollection=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser openFile = new JFileChooser();
        int retrieval = openFile.showDialog(null, "Open file");
        if (retrieval == JFileChooser.APPROVE_OPTION) {
            try {
                File file = openFile.getSelectedFile();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                NodeList nList = doc.getElementsByTagName("Day");
                int beginIndex=0;
                for (int itemNumber = 0; itemNumber < nList.getLength(); itemNumber++) {

                    TimeTableModel newTable = new TimeTableModel();
                    KeyDateObject newKeyDate = new KeyDateObject();
                    Node nNode = nList.item(itemNumber);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element keyInf = (Element) nNode;
                        Element dayContent = (Element) nNode;

                        newKeyDate.setDay(keyInf.getElementsByTagName("day").item(0).getTextContent());
                        newKeyDate.setMonth(keyInf.getElementsByTagName("month").item(0).getTextContent());
                        int year = new Integer(keyInf.getElementsByTagName("year").item(0).getTextContent());
                        newKeyDate.setYear(year);

                        NodeList nnList = doc.getElementsByTagName("TimeNote");
                        int numOfNotes = new Integer(dayContent.getElementsByTagName("numOfNotes").item(0).getTextContent());
                        for (int noteIndex = beginIndex; noteIndex < beginIndex+numOfNotes; noteIndex++)
                        {
                            Node nnNode = nnList.item(noteIndex);
                            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                                Element timeNote = (Element) nnNode;
                                NoteModel newNote = new NoteModel();
                                newNote.setNote(timeNote.getElementsByTagName("note").item(0).getTextContent());
                                NoteTimeModel newTimeNote = new NoteTimeModel(newNote, timeNote.getElementsByTagName("time").item(0).getTextContent());
                                newTable.addNoteTime(newTimeNote);}
                        }
                        beginIndex+=numOfNotes;

                    }
                    modelCollection.put(newKeyDate, newTable);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
