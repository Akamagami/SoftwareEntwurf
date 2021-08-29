package frontend.pages;

import backend.event.Event;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.EventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class TeilEventDetailsHilfsmittel extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement addButton;
    private ButtonElement deleteButton;
    private SimpleListComponent simpleListComponent;

    public TeilEventDetailsHilfsmittel() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                .font( new Font("SansSerif", Font.ITALIC,10))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        addButton = ButtonElement.builder("TEDH-ADDBTN").buttonText("Hinzufügen").build();
        deleteButton = ButtonElement.builder("TEDH-DELBTN").buttonText("Löschen").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }


    public void displayEvents(ArrayList<backend.event.Event> events) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (Event event : events ) {
            elems.add(new EventUI(event));
        }
        this.simpleListComponent.setListElements(elems);
    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        this.simpleListComponent.addObserver(controller);
        this.addButton.addObserver(controller);
        this.deleteButton.addObserver(controller);
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

}
