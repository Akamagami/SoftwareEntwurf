package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import execution.Main;
import frontend.controller.GUIController;


import javax.swing.*;
import java.awt.*;
import java.util.EventListener;


public class EventDetailsTeilevent extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement createButton;
    private SimpleListComponent simpleListComponent;

    public EventDetailsTeilevent() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                                .font( new Font("SansSerif", Font.ITALIC,10))
                                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        createButton = ButtonElement.builder("EDT-CRBTN").buttonText("Teilevent erstellen").build();


        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(createButton, BorderLayout.SOUTH);
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
        createButton.addObserver(controller);
        simpleListComponent.addObserver(controller);
    }
}
