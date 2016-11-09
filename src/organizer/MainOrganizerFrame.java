package organizer;

import organizer.model.DayCollection;
import organizer.model.NoteModel;
import organizer.model.NoteTimeModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Katerina on 25.09.2016.
 */
public class MainOrganizerFrame extends JFrame {

    private final int DEFAULT_WIDTH = 900;
    private final int DEFAULT_HEIGHT = 495;

    private DayCollection modelCollection;

    private TablePanel dayTablePanel = new TablePanel(modelCollection);
    private CalendarInfPanel calendarInfPanel = new CalendarInfPanel();
    private ButtonPanel buttonPanel = new ButtonPanel(modelCollection);
    private CalendarPanel calendarPanel = new CalendarPanel();

    private JButton newNoteButton = new JButton("New");
    private JButton editNoteButton = new JButton("Edit");
    private JButton deleteNoteButton = new JButton("Delete");

    ListSelectionModel rowSelectionModel = calendarPanel.calendarTable.getSelectionModel();
    ListSelectionModel columnSelectionModel = calendarPanel.getColModel().getSelectionModel();
    public MainOrganizerFrame(DayCollection model) {
        this.modelCollection = model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setName("Organizer");
        setLayout(new BorderLayout());
        add(calendarInfPanel, BorderLayout.WEST);
        add(dayTablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        setResizable(false);
        setVisible(true);

        calendarInfPanel.add(calendarPanel, BorderLayout.CENTER);

        buttonPanel.add(newNoteButton);
        buttonPanel.add(editNoteButton);
        buttonPanel.add(deleteNoteButton);

        setLocationRelativeTo(null);

        rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dayTablePanel.updateTablePanel(calendarPanel.getCurrentKey(), model);
            }
        });

        columnSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dayTablePanel.updateTablePanel(calendarPanel.getCurrentKey(), model);
            }
        });

        newNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (calendarPanel.getCurrentKey().getDay()==null || calendarPanel.getCurrentKey()==null)
                {
                    JOptionPane.showMessageDialog(dayTablePanel, "Choose date on the calendar to add new note.");
                }
                else if (!dayTablePanel.isRowSelected())
                {
                    JOptionPane.showMessageDialog(dayTablePanel, "Choose the time of the day to add new note.");
                }

                else {AddNoteDialog newEntryDialog = new AddNoteDialog(null, modelCollection, calendarPanel.getCurrentKey(), dayTablePanel.getDayTime(), dayTablePanel);
                newEntryDialog.setVisible(true);}

            }
        });

        editNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.containsKey(calendarPanel.getCurrentKey()))
                    JOptionPane.showMessageDialog(dayTablePanel, "Choose the cell with note.");
                else if ( model.get(calendarPanel.getCurrentKey()).getValueAt(1,
                                model.get(calendarPanel.getCurrentKey()).getRowNumber(dayTablePanel.getDayTime()))=="")
                 JOptionPane.showMessageDialog(dayTablePanel, "Choose the cell with note.");
                else {
                    EditNoteDialog newEntryDialog = new EditNoteDialog(null, model, model.get(calendarPanel.getCurrentKey()).getValueAt(1,
                            model.get(calendarPanel.getCurrentKey()).getRowNumber(dayTablePanel.getDayTime())),
                            calendarPanel.getCurrentKey(), dayTablePanel.getDayTime(), dayTablePanel);
                    newEntryDialog.setVisible(true);
                }

            }});

        deleteNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                NoteModel note = new NoteModel();
                NoteTimeModel deletedNote = new NoteTimeModel(note, dayTablePanel.getDayTime());

                model.get(calendarPanel.getCurrentKey()).removeNoteTime(deletedNote);
                dayTablePanel.updateTablePanel(calendarPanel.getCurrentKey(), model);
            }catch (Exception w) {};
        }

        });
    }

    public TablePanel getDayTablePanel() {return this.dayTablePanel;}
}