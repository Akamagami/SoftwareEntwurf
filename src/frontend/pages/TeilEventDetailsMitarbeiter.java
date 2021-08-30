package frontend.pages;

import backend.benutzer.Benutzer;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.MitarbeiterUI;
import frontend.UIData.TeilEventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TeilEventDetailsMitarbeiter extends JPanel {

    private GUIController controller;
    private ButtonElement addButton;
    private ButtonElement deleteButton;
    private SimpleListComponent simpleListComponent;
    private TeilEventUI currentTeilEventUI;


    public TeilEventDetailsMitarbeiter() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                .font( new Font("SansSerif", Font.ITALIC,20))
                .selectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION).build();

        addButton = ButtonElement.builder("TEDM-ADDBTN").buttonText("Hinzufügen").build();
        deleteButton = ButtonElement.builder("TEDM-DELBTN").buttonText("Löschen").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        this.setLayout(new BorderLayout());
        this.add(simpleListComponent, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //Ausfuellen des SimpleListComponents mit im Teilevent hinzugefuegten Mitarbeitern
    public void displayMitarbeiter(Benutzer[] benutzer) {
        if (currentTeilEventUI.getTeilEvent().hatGruppe()) {
            ArrayList<IDepictable> elems = new ArrayList();
            for (Benutzer nutzer : benutzer ) {
                elems.add(new MitarbeiterUI(nutzer));
            }
            this.simpleListComponent.setListElements(elems);
        }

    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        this.simpleListComponent.addObserver(controller);
        this.addButton.addObserver(controller);
        this.deleteButton.addObserver(controller);
    }

    //Loeschen aller Elemente des SimpleListComponents
    public void clearTeilevents() {
        this.simpleListComponent.removeAllListElements();
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
