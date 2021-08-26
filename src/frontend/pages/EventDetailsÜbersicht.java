package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class EventDetailsÜbersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {
    GUIController controller;
    SimpleListComponent simpleListComponent;

    public EventDetailsÜbersicht() {
        simpleListComponent = SimpleListComponent.builder("SLC")
                                .font(new Font("SansSerif", Font.ITALIC,10))
                                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
                                .build();


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
