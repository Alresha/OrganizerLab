package organizer;

import organizer.model.DayCollection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import organizer.model.NoteTimeModel;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveFileListener implements ActionListener {
    private DayCollection modelCollection;

    public SaveFileListener(DayCollection model){
        this.modelCollection=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser saveFile = new JFileChooser();
            int retrieval = saveFile.showSaveDialog(null);
            if (retrieval == JFileChooser.APPROVE_OPTION) {
                File file = saveFile.getSelectedFile();
                if (!file.exists()) {
                    file.createNewFile();
                }
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("DayCollection");
                doc.appendChild(root);
                for (KeyDateObject key : modelCollection.keySet()) {
                    Element dayKeyElement = doc.createElement("Day");
                    root.appendChild(dayKeyElement);
                    Element keyInf = doc.createElement("keyInf");
                    dayKeyElement.appendChild(keyInf);
                    createElement(doc, keyInf, "day", modelCollection.get(key).getKeyDateObject().getDay().toString());
                    createElement(doc, keyInf, "month", modelCollection.get(key).getKeyDateObject().getMonth());
                    createElement(doc, keyInf, "year", Integer.toString(modelCollection.get(key).getKeyDateObject().getYear()));

                    Element dayContentElement = doc.createElement("Content");
                    dayKeyElement.appendChild(dayContentElement);
                    createElement(doc, dayContentElement, "numOfNotes", Integer.toString(modelCollection.get(key).getNotesPack().size()));
                    for (NoteTimeModel note : modelCollection.get(key).getNotesPack()) {
                        Element timeNote = doc.createElement("TimeNote");
                        dayContentElement.appendChild(timeNote);
                        createElement(doc, timeNote, "note", note.getNoteString());
                        createElement(doc, timeNote, "time", note.getDayTime());
                    }
                }
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                DOMSource domSource = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(domSource, result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createElement(Document doc, Element parent, String field, String value) {
        Element element = doc.createElement(field);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}