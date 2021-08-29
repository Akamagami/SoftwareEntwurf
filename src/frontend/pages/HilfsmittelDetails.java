package frontend.pages;


import backend.datenbasis.Speicher;
import backend.hilfsmittel.Hilfsmittel;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.HilfsmittelUI;
import frontend.UIData.TeilEventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class HilfsmittelDetails extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement addButton;
    private SimpleListComponent simpleListComponent;
    private TeilEventUI currentTeilEventUI;

    public HilfsmittelDetails() {
        simpleListComponent = SimpleListComponent.builder("HD-SLC")
                .font( new Font("SansSerif", Font.ITALIC,20))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        addButton = ButtonElement.builder("HD-BTN").buttonText("Hinzufügen").build();

        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(addButton, BorderLayout.SOUTH);
    }

    //Darstellen der Hilfsmittel in SimpleListComponent
    public void displayHilfsmittel(ArrayList<Hilfsmittel> hilfsmittelArrayList, TeilEventUI currentTeilEventUI, Speicher speicher) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (Hilfsmittel hilfsmittel : hilfsmittelArrayList ) {
            elems.add(new HilfsmittelUI(hilfsmittel, speicher, currentTeilEventUI.getTeilEvent().getStart(), currentTeilEventUI.getTeilEvent().getEnde()));
        }
        this.simpleListComponent.setListElements(elems);
    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        this.simpleListComponent.addObserver(controller);
        this.addButton.addObserver(controller);
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

    public GUIController getController() {
        return controller;
    }

    public ButtonElement getAddButton() {
        return addButton;
    }

    public void setAddButton(ButtonElement returnButton) {
        this.addButton = returnButton;
    }

    public SimpleListComponent getSimpleListComponent() {
        return simpleListComponent;
    }

    public void setSimpleListComponent(SimpleListComponent simpleListComponent) {
        this.simpleListComponent = simpleListComponent;
    }

    public TeilEventUI getCurrentTeilEventUI() {
        return currentTeilEventUI;
    }

    public void setCurrentTeilEventUI(TeilEventUI currentTeilEventUI) {
        this.currentTeilEventUI = currentTeilEventUI;
    }
}
