package frontend.pages;

import backend.event.Event;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.EventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListGUI extends JPanel {

    private SimpleListComponent simpleListComponent;
    private ButtonElement buttonElement;
    private ButtonElement editButton;
    private GUIController controller;
    private ButtonElement deleteButton;

    public EventListGUI() {
        simpleListComponent = SimpleListComponent.builder("SLC")
                .font(new Font("SansSerif", Font.ITALIC,20))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
                .build();

        buttonElement = ButtonElement.builder("BTN-CREATE-EVENT").buttonText("Event erstellen").build();
        editButton = ButtonElement.builder("BTN-EDIT-EVENT").buttonText("Event bearbeiten").build();
        deleteButton = ButtonElement.builder("BTN-DELETE-EVENT").buttonText("Event löschen").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonElement);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        buttonElement.addObserver(controller);
        editButton.addObserver(controller);
        simpleListComponent.addObserver(controller);
        deleteButton.addObserver(controller);
    }

    //Ausfuellen des SimpleListComponents mit Events
    public void displayEvents(ArrayList<Event> events) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (Event event : events ) {
            elems.add(new EventUI(event));
        }
        this.simpleListComponent.setListElements(elems);
    }

    public SimpleListComponent getSimpleListComponent() {
        return simpleListComponent;
    }

    public ButtonElement getButtonElement() {
        return buttonElement;
    }

    public void setButtonElement(ButtonElement buttonElement) {
        this.buttonElement = buttonElement;
    }

    public GUIController getController() {
        return controller;
    }

}
