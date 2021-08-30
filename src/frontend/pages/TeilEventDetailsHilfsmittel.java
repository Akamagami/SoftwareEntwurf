package frontend.pages;

import backend.hilfsmittel.Zuweisung;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.ZuweisungUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TeilEventDetailsHilfsmittel extends JPanel {

    private GUIController controller;
    private ButtonElement addButton;
    private ButtonElement deleteButton;
    private SimpleListComponent simpleListComponent;

    public TeilEventDetailsHilfsmittel() {
        simpleListComponent = SimpleListComponent.builder("EDT-SLC")
                .font( new Font("SansSerif", Font.ITALIC,20))
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

    //Ausfuellen des SimpleListComponents mit im Teilevent hinzugefuegten Hilfsmitteln
    public void displayHilfsmittel(ArrayList<Zuweisung> zuweisungArrayList) {
        ArrayList<IDepictable> elems = new ArrayList();
        for (Zuweisung zuweisung : zuweisungArrayList ) {
            elems.add(new ZuweisungUI(zuweisung));
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

    public GUIController getController() {
        return controller;
    }

    public SimpleListComponent getSimpleListComponent() {
        return simpleListComponent;
    }

}
