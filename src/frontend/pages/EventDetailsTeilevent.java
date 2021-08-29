package frontend.pages;

import backend.event.TeilEvent;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.TeilEventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;


public class EventDetailsTeilevent extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement createButton;
    private ButtonElement editButton;
    private ButtonElement deleteButton;
    private SimpleListComponent simpleListComponent;

    public EventDetailsTeilevent() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                                .font( new Font("SansSerif", Font.ITALIC,20))
                                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        createButton = ButtonElement.builder("EDT-CRBTN").buttonText("Teilevent erstellen").build();
        editButton = ButtonElement.builder("EDT-EDTBTN").buttonText("Teilevent bearbeiten").build();
        deleteButton = ButtonElement.builder("EDT-DELBTN").buttonText("Teilevent löschen").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        createButton.addObserver(controller);
        editButton.addObserver(controller);
        deleteButton.addObserver(controller);
        simpleListComponent.addObserver(controller);
    }


    public void displayTeilEvents(ArrayList<TeilEvent> teilEvents) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (TeilEvent teilEvent : teilEvents ) {
            elems.add(new TeilEventUI(teilEvent));
        }
        this.simpleListComponent.setListElements(elems);
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

    public GUIController getController() {
        return controller;
    }

    public ButtonElement getCreateButton() {
        return createButton;
    }

    public void setCreateButton(ButtonElement createButton) {
        this.createButton = createButton;
    }

    public SimpleListComponent getSimpleListComponent() {
        return simpleListComponent;
    }

    public void setSimpleListComponent(SimpleListComponent simpleListComponent) {
        this.simpleListComponent = simpleListComponent;
    }

}
