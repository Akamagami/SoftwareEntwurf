package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import frontend.controller.GUIController;

import javax.swing.*;
import java.util.EventListener;


public class EventDetailsTeilevent extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement returnButton;

    public EventDetailsTeilevent() {
        returnButton = ButtonElement.builder("BTN-RETURN").buttonText("Zurück").build();
        this.add(returnButton);
    }

    @Override
    public boolean removeObserver(EventListener eventListener) {
        return false;
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {

    }

    @Override
    public boolean addObserver(EventListener eventListener) {
        return false;
    }

    public void setController(GUIController controller) {
        this.controller = controller;
    }
}
