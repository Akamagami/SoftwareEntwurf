package frontend.pages;

import constants.EventKategorie;
import constants.Status;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import frontend.UIData.EventUI;
import frontend.controller.GUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class EventDetailsUebersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement safeButton;
    private SlideshowComponent ssc;
    private AttributeComponent attributeComponent;
    private AttributeComponent kontaktBeschreibungComponent;


    public EventDetailsUebersicht() {

        String[] kategorien = new String[EventKategorie.values().length];
        int count = 0;
        for (EventKategorie kategorie : EventKategorie.values()) {
            kategorien[count] = kategorie.getDisplayName();
            count++;
        }

        String[] status = new String[Status.values().length];
        int count2 = 0;
        for (Status status1 : Status.values()) {
            status[count2] = status1.getDisplayName();
            count2++;
        }

        //Erzeugung der Attributfelder
        AttributeElement[] attributeElements = new AttributeElement[] {
                AttributeElement.builder("AE-1").labelName("Titel")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(100).build(),

                AttributeElement.builder("AE-2").labelName("Beschreibung")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(300).build(),

                AttributeElement.builder("AE-3").labelName("Kunde")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(100).toolTip("Vorname Nachname").build(),

                AttributeElement.builder("AE-4").labelName("Von")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_DATE)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-5").labelName("Bis")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_DATE)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-6").labelName("Besucher")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_NUMBER)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-7").labelName("Budget")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_NUMBER)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-8").labelName("Kategorie")
                .actionType(AttributeElement.ActionType.COMBOBOX)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(300).data(kategorien).build()

        };

        //Attributfelder speziell für Kontaktdaten
        AttributeElement[] kontaktBeschreibung = new AttributeElement[] {

                AttributeElement.builder("K-1").labelName("Kontaktperson")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .mandatory(true).maxLength(100).toolTip("Vorname Nachname").build(),

                AttributeElement.builder("K-2").labelName("E-Mail")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .mandatory(true).maxLength(150).build(),

                AttributeElement.builder("K-3").labelName("Handynummer")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .allowedChars(AttributeElement.CHARSET_NUMBER)
                        .mandatory(true).maxLength(100).build()
        };


        attributeComponent = AttributeComponent.builder("AC-1").attributeElements(attributeElements).title("EventUebersicht").build();
        kontaktBeschreibungComponent = AttributeComponent.builder("K-1").attributeElements(kontaktBeschreibung).title("Kontaktinformationen").build();
        safeButton = ButtonElement.builder("SAFE-EDUE").buttonText("Speichern").build();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(safeButton);


        this.setLayout(new GridLayout(2,2));
        this.add(attributeComponent);
        this.add(kontaktBeschreibungComponent);
        this.add(buttonPanel);

    }

    //setzt den Controller und fügt sie gleichzeitig allen SWE-Utils-Komponenten als Observer hinzu
    public void setController(GUIController controller) {
        this.controller = controller;
        safeButton.addObserver(controller);

    }

    //Ausfuellen der Attributfelder mit Eventdaten
    public void fillAttributes(EventUI event) {
        String[] kategorien = new String[EventKategorie.values().length];
        int count = 0;
        for (EventKategorie kategorie : EventKategorie.values()) {
            kategorien[count] = kategorie.getDisplayName();
            count++;
        }

        String[] status = new String[Status.values().length];
        int count2 = 0;
        for (Status status1 : Status.values()) {
            status[count2] = status1.getDisplayName();
            count2++;
        }

        this.attributeComponent.setAttributeElements(
                new AttributeElement[] {
                        AttributeElement.builder("AE-1").labelName("Titel")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(100).value(event.getEvent().getTitel()).build(),

                        AttributeElement.builder("AE-2").labelName("Beschreibung")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(300).value(event.getEvent().getBeschreibung()).build(),

                        AttributeElement.builder("AE-3").labelName("Kunde")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(100).toolTip("Vorname Nachname")
                                .value(event.getEvent().getKunde()).build(),

                        AttributeElement.builder("AE-4").labelName("Von")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DATE)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(event.getEvent().getStart()).build(),

                        AttributeElement.builder("AE-5").labelName("Bis")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DATE)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(event.getEvent().getEnd()).build(),

                        AttributeElement.builder("AE-6").labelName("Besucher")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_NUMBER)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(event.getEvent().getBesucher()).build(),

                        AttributeElement.builder("AE-7").labelName("Budget")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_NUMBER)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(event.getEvent().getBudget()).build(),

                        AttributeElement.builder("AE-8").labelName("Kategorie")
                                .actionType(AttributeElement.ActionType.COMBOBOX)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(300).data(kategorien)
                                .value(event.getEvent().getKategorie().getDisplayName()).build(),

                        AttributeElement.builder("AE-9").labelName("Status")
                                .actionType(AttributeElement.ActionType.COMBOBOX)
                                .modificationType(AttributeElement.ModificationType.NONE)
                                .mandatory(true).maxLength(300).data(status)
                                .value(event.getEvent().getStatus().getDisplayName()).build()

                });

        this.kontaktBeschreibungComponent.setAttributeElements(
                new AttributeElement[] {

                        AttributeElement.builder("K-1").labelName("Kontaktperson")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(100).toolTip("Vorname Nachname").value(event.getEvent().getKontaktInfoList().getKontaktInfos()[0].getName()).build(),

                        AttributeElement.builder("K-2").labelName("E-Mail")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(150).value(event.getEvent().getKontaktInfoList().getKontaktInfos()[0].getEmail()).build(),

                        AttributeElement.builder("K-3").labelName("Handynummer")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_NUMBER)
                                .mandatory(true).maxLength(100).value(event.getEvent().getKontaktInfoList().getKontaktInfos()[0].getTele()).build()
                });
    }


    public GUIController getController() {
        return controller;
    }

    public ButtonElement getSafeButton() {
        return safeButton;
    }

    public void setSafeButton(ButtonElement safeButton) {
        this.safeButton = safeButton;
    }

    public SlideshowComponent getSsc() {
        return ssc;
    }

    public void setSsc(SlideshowComponent ssc) {
        this.ssc = ssc;
    }

    public AttributeComponent getAttributeComponent() {
        return attributeComponent;
    }

    public void setAttributeComponent(AttributeComponent attributeComponent) {
        this.attributeComponent = attributeComponent;
    }

    public AttributeComponent getKontaktBeschreibungComponent() {
        return kontaktBeschreibungComponent;
    }

    public void setKontaktBeschreibungComponent(AttributeComponent kontaktBeschreibungComponent) {
        this.kontaktBeschreibungComponent = kontaktBeschreibungComponent;
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
