package organizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;



/**
 * Created by Katerina on 02.10.2016.
 */
public class CalendarPanel  extends JPanel{
    DefaultTableModel model;
    Calendar calendar = new GregorianCalendar();
    JLabel label;
    private String monthText;
    JTable calendarTable;
    int year;


    public CalendarPanel(){
            this.setSize(300,200);
            this.setLayout(new BorderLayout());
            this.setVisible(true);

            label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JButton previousMonth = new JButton("<<");
            previousMonth.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    calendar.add(Calendar.MONTH, -1);
                    updateMonth();
                }
            });

            JButton nextMonth = new JButton(">>");
            nextMonth.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    calendar.add(Calendar.MONTH, +1);
                    updateMonth();
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(previousMonth,BorderLayout.WEST);
            panel.add(label,BorderLayout.CENTER);
            panel.add(nextMonth,BorderLayout.EAST);


            String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
            model = new DefaultTableModel(null,columns);
            calendarTable = new JTable(model);
            JScrollPane pane = new JScrollPane(calendarTable);
            calendarTable.setCellSelectionEnabled(true);

             for (int rowIndex = 0; rowIndex<model.getRowCount(); rowIndex++)
                {
                    calendarTable.setRowHeight(rowIndex, 35);
                }

            this.add(panel,BorderLayout.NORTH);
            this.add(pane,BorderLayout.CENTER);

            this.updateMonth();

        }

        void updateMonth() {
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            monthText = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
            year = calendar.get(Calendar.YEAR);
            label.setText(monthText + " " + year);

            int startDay = calendar.get(Calendar.DAY_OF_WEEK);
            int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

            model.setRowCount(0);
            model.setRowCount(weeks + 1);

            //calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            calendarTable.setRowSelectionAllowed(true);
            calendarTable.setColumnSelectionAllowed(true);
            int dayIndex = startDay - 1;
            for (int dayOfMonth = 1; dayOfMonth <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); dayOfMonth++) {
                model.setValueAt(dayOfMonth, (dayIndex) / 7, (dayIndex) % 7);
                dayIndex = dayIndex + 1;
            }
        }

    public KeyDateObject getCurrentKey()
    {
        KeyDateObject currentDate = new KeyDateObject();
        try{
        currentDate.setDay(model.getValueAt(calendarTable.getSelectedRow(), calendarTable.getSelectedColumn()));
        currentDate.setMonth(monthText);
        currentDate.setYear(year);} catch (Exception e){
        };

        return currentDate;
    }

    public TableColumnModel getColModel(){
        return calendarTable.getColumnModel();
    }
}