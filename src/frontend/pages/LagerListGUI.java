package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class LagerListGUI extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private SimpleListComponent simpleListComponent;

    public LagerListGUI() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                .font( new Font("SansSerif", Font.ITALIC,10))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
    }

    @Override
    public boolean addObserver(EventListener eventListener) {
        return false;
    }

    @Override
    public boolean removeObserver(EventListener eventListener) {
        return false;
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {

    }

    public void setController(GUIController controller) {
        this.controller = controller;
    }
}
