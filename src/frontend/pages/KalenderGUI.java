package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.CalendarComponent;
import frontend.controller.GUIController;

import javax.swing.*;
import java.util.EventListener;

public class KalenderGUI extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private CalendarComponent calendarComponent;
    private GUIController controller;

    public KalenderGUI() {
        calendarComponent = CalendarComponent.builder("KC").build();
        this.add(calendarComponent);
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
