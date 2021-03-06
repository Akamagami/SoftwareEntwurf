package frontend.pages;

import backend.benutzer.Benutzer;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.MitarbeiterUI;
import frontend.UIData.TeilEventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MitarbeiterDetails extends JPanel {

    private GUIController controller;
    private ButtonElement addButton;
    private SimpleListComponent simpleListComponent;
    private TeilEventUI currentTeilEventUI;


    public MitarbeiterDetails() {
        simpleListComponent = SimpleListComponent.builder("MD-SLC")
                .font( new Font("SansSerif", Font.ITALIC,20))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        addButton = ButtonElement.builder("MD-BTN").buttonText("Hinzufügen").build();

        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(addButton, BorderLayout.SOUTH);
    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        this.simpleListComponent.addObserver(controller);
        this.addButton.addObserver(controller);
    }

    //Ausfuellen des SimpleListComponents mit Mitarbeitern
    public void displayMitarbeiter(ArrayList<Benutzer> benutzer) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (Benutzer nutzer : benutzer ) {
            elems.add(new MitarbeiterUI(nutzer));
        }
        this.simpleListComponent.setListElements(elems);
    }

    public GUIController getController() {
        return controller;
    }

    public SimpleListComponent getSimpleListComponent() {
        return simpleListComponent;
    }

    public void setCurrentTeilEventUI(TeilEventUI currentTeilEventUI) {
        this.currentTeilEventUI = currentTeilEventUI;
    }

}


