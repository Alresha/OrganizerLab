package organizer;

import organizer.model.DayCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Katerina on 25.09.2016.
 */
public class OrganizerLab {
    private DayCollection modelCollection = new DayCollection();
    MainOrganizerFrame program = new MainOrganizerFrame(modelCollection);

    public static void main(String[] args)

            throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                OrganizerLab lab = new OrganizerLab();
            }
        });
    }
}
