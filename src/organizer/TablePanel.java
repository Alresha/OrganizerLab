package organizer;

import organizer.model.DayCollection;
import organizer.model.TimeTableModel;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TablePanel extends JPanel {
    private DateInfPanel dateInfPanel = new DateInfPanel();

    private DayCollection modelCollection;
    private TimeTableModel timetable;

    private final int DEFAULT_WIDTH = 510;
    private final int DEFAULT_HEIGHT = 495;

    private final int TABLE_WIDTH = 510;
    private final int TABLE_HEIGHT = 400;

    private String[] dayTimeNames = {"7-9", "9-11", "11-13", "13-15", "15-17", "17-19", "19-21"};

    private JTable dayTable = new JTable(7,2);

    public TablePanel(DayCollection model) {
        this.modelCollection = model;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBorder(BorderFactory.createLoweredBevelBorder());

        dayTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        dayTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        dayTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        dayTable.getColumnModel().getColumn(0).setMinWidth(108);
        dayTable.getColumnModel().getColumn(0).setMaxWidth(108);
        dayTable.getColumnModel().getColumn(1).setMinWidth(390);
        dayTable.getColumnModel().getColumn(1).setMaxWidth(390);

        dayTable.setFont(new Font("", Font.ITALIC, 20));
        dayTable.setAlignmentX(CENTER_ALIGNMENT);
        dayTable.setAlignmentY(CENTER_ALIGNMENT);

        for (int rowIndex = 0; rowIndex < 7; rowIndex++) {
            dayTable.setRowHeight(rowIndex, 57);
            dayTable.setValueAt(dayTimeNames[rowIndex], rowIndex, 0);
        }

        dayTable.setBackground(Color.LIGHT_GRAY);
        dayTable.setGridColor(Color.DARK_GRAY);
        dayTable.setBorder(BorderFactory.createEtchedBorder());
        dayTable.setSelectionBackground(Color.GRAY);

        setSize(TABLE_WIDTH, TABLE_HEIGHT);

        add(dateInfPanel, BorderLayout.NORTH);
        add(dayTable, BorderLayout.CENTER);


    }

   public void updateTablePanel(KeyDateObject keyDate, DayCollection model) {
        if (model.containsKey(keyDate)) {
            for (int rowIndex = 0; rowIndex < 7; rowIndex++) {
                if (model.get(keyDate).getValueAt(1, rowIndex)!=null)
                dayTable.setValueAt(model.get(keyDate).getValueAt(1, rowIndex), rowIndex, 1);
            }
        }
       else {for (int rowIndex=0; rowIndex< 7; rowIndex++){
            dayTable.setValueAt(null, rowIndex,1);
        }}
    }

    public String getDayTime() {
        String dayTime = new String();
        try {
            dayTime = dayTimeNames[dayTable.getSelectedRow()];
        } catch (Exception e) {}
        return dayTime;
    }

    public Boolean isRowSelected(){
        Boolean returnedValue=false;
        for (int rowIndex=0; rowIndex<7; rowIndex++)
        {
            if (dayTable.getSelectedRow()==rowIndex)
                returnedValue=true;
        }
        return returnedValue;
    }
}