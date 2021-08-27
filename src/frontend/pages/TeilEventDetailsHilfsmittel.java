package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import execution.Main;
import frontend.controller.GUIController;
import frontend.controller.MainGUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class TeilEventDetailsHilfsmittel extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement returnButton;
    private SimpleListComponent simpleListComponent;

    public TeilEventDetailsHilfsmittel() {
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
