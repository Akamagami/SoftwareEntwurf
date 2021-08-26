package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import frontend.controller.GUIController;

import javax.swing.*;
import java.util.EventListener;

public class TeilEventDetailsÜbersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {

    GUIController controller;



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
