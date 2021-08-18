package frontend;

import frontend.panes.EventDetailsPane;
import frontend.panes.LagerDetailsPane;
import frontend.panes.OverviewPane;
import frontend.panes.TeilEventDetailsPane;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JComponent {

    private JFrame frame;
    private JTabbedPane jTabbedPane;
    private EventDetailsPane eventpane;
    private LagerDetailsPane lagerpane;
    private OverviewPane overviewpane;
    private TeilEventDetailsPane teileventspane;

    public MainGUI() {
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setTitle("EventPlaner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));

        jTabbedPane = new JTabbedPane(1);

        eventpane = new EventDetailsPane();
        lagerpane = new LagerDetailsPane();
        overviewpane = new OverviewPane();
        teileventspane = new TeilEventDetailsPane();

        jTabbedPane.addTab(eventpane);



        frame.add(eventpane);
        frame.add(lagerpane);
        frame.add(overviewpane);
        frame.add(teileventspane);

        frame.setVisible(true);

    }
}
