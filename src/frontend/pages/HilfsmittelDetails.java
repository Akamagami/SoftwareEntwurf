package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import frontend.controller.GUIController;


import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class HilfsmittelDetails extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement returnButton;
    private SimpleListComponent simpleListComponent;


    public HilfsmittelDetails() {


        simpleListComponent = SimpleListComponent.builder("SLC").build();

        this.setLayout(new GridLayout(2,2));
        this.add(simpleListComponent);
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

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
    }

}
