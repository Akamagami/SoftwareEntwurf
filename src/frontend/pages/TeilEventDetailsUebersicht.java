package frontend.pages;

import backend.benutzer.Kontaktinformation;
import backend.event.Event;
import backend.event.TeilEvent;
import backend.event.eventelement.Catering;
import backend.event.eventelement.Location;
import backend.event.eventelement.Musik;
import backend.event.eventelement.Sonstiges;
import constants.ClassType;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.EventUI;
import frontend.UIData.TeilEventUI;
import frontend.controller.GUIController;
import frontend.controller.MainGUIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class TeilEventDetailsUebersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement saveButton;
    private AttributeComponent attributeComponent;
    private AttributeComponent specialComponent;
    private AttributeComponent kontaktInformation;
    private AttributeElement[] elements;
    private Object teilEventIdent;
    private EventUI currentEventUI;

    public TeilEventDetailsUebersicht() {


        AttributeElement[] attributeElements = new AttributeElement[] {
                AttributeElement.builder("AE-1").labelName("Name")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .mandatory(true).maxLength(100).toolTip("Vorname Nachname").build(),

                AttributeElement.builder("AE-2").labelName("Beschreibung")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .mandatory(true).maxLength(200).build(),

                AttributeElement.builder("AE-3").labelName("Von")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .allowedChars(AttributeElement.CHARSET_DATE)
                        .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-4").labelName("Bis")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .allowedChars(AttributeElement.CHARSET_DATE)
                        .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-5").labelName("Kosten")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .allowedChars(AttributeElement.CHARSET_NUMBER)
                        .mandatory(true).maxLength(20).build()

        };

        AttributeElement[] kontaktInformationElements =  new AttributeElement[] {

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

        elements = new AttributeElement[] {
                AttributeElement.builder("S-1").labelName("Zuständiger")
                        .actionType(AttributeElement.ActionType.NONE)
                        .modificationType(AttributeElement.ModificationType.DIRECT)
                        .allowedChars(AttributeElement.CHARSET_DEFAULT)
                        .mandatory(true).maxLength(200).build()
        };

        saveButton = ButtonElement.builder("TED-SAVE-BUTTON").buttonText("Speichern").build();

        attributeComponent = AttributeComponent.builder("AC-1").attributeElements(attributeElements).title("EventUebersicht").build();
        specialComponent = AttributeComponent.builder("AC-2").attributeElements(elements).title("Spezial").build();
        kontaktInformation = AttributeComponent.builder("AC-3").attributeElements(kontaktInformationElements).title("Kontaktinformation").build();

        this.setLayout(new GridLayout(2,2));
        this.add(attributeComponent);
        this.add(specialComponent);
        this.add(kontaktInformation);
        this.add(saveButton);
    }


    public void setTeilEventIdent(Object teilEventIdent) {
        this.teilEventIdent = teilEventIdent;

        if (teilEventIdent == "Sonstiges") {
            elements = new AttributeElement[] {
                    AttributeElement.builder("S-1").labelName("Zuständiger")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(200).build()
            };
        } else if (teilEventIdent == "Catering") {
            elements = new AttributeElement[]{
                    AttributeElement.builder("C-1").labelName("Speiseangebot")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(300).build(),

                    AttributeElement.builder("C-2").labelName("MenUebeschreibung")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(300).build()
            };
        } else if (teilEventIdent == "Location") {
            elements = new AttributeElement[]{
                    AttributeElement.builder("L-1").labelName("Adresse")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(300).build(),

                    AttributeElement.builder("L-2").labelName("Größe")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(300).build()
            };
        } else if (teilEventIdent == "Musik") {
            elements = new AttributeElement[]{
                    AttributeElement.builder("M-1").labelName("KUenstler")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(50).build(),

                    AttributeElement.builder("M-2").labelName("Genre")
                            .actionType(AttributeElement.ActionType.NONE)
                            .modificationType(AttributeElement.ModificationType.DIRECT)
                            .allowedChars(AttributeElement.CHARSET_DEFAULT)
                            .mandatory(true).maxLength(100).build()
            };
        }
        specialComponent.setAttributeElements(elements);
    }

    public void fillAttributes(TeilEventUI teilEventUI) {
        TeilEvent teilEvent = teilEventUI.getTeilEvent();

        this.attributeComponent.setAttributeElements(
                new AttributeElement[]{
                        AttributeElement.builder("AE-1").labelName("Name")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(100).toolTip("Vorname Nachname")
                                .value(teilEvent.getName()).build(),

                        AttributeElement.builder("AE-2").labelName("Beschreibung")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(200)
                                .value(teilEvent.getElement().getBeschreibung()).build(),

                        AttributeElement.builder("AE-3").labelName("Von")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DATE)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(teilEvent.getStart()).build(),

                        AttributeElement.builder("AE-4").labelName("Bis")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DATE)
                                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY")
                                .value(teilEvent.getEnde()).build(),

                        AttributeElement.builder("AE-5").labelName("Kosten")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_NUMBER)
                                .mandatory(true).maxLength(20)
                                .value(teilEvent.getElement().getKosten()).build()
                });

        Kontaktinformation kontaktinformation = teilEvent.getElement().getKontaktInfoList().getKontaktInfos()[0];
        this.kontaktInformation.setAttributeElements(
                new AttributeElement[] {
                        AttributeElement.builder("K-1").labelName("Kontaktperson")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(100).toolTip("Vorname Nachname")
                                .value(kontaktinformation.getName()).build(),

                        AttributeElement.builder("K-2").labelName("E-Mail")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .mandatory(true).maxLength(150)
                                .value(kontaktinformation.getEmail()).build(),

                        AttributeElement.builder("K-3").labelName("Handynummer")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_NUMBER)
                                .mandatory(true).maxLength(100)
                                .value(kontaktinformation.getTele()).build()
                }
        );

        if (teilEvent.getElement().getType() == ClassType.ELEMENTCATERING) {
            Catering catering = (Catering) teilEvent.getElement();
            this.specialComponent.setAttributeElements(
                    new AttributeElement[] {
                            AttributeElement.builder("C-1").labelName("Speiseangebot")
                                    .actionType(AttributeElement.ActionType.NONE)
                                    .modificationType(AttributeElement.ModificationType.DIRECT)
                                    .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                    .mandatory(true).maxLength(300)
                                    .value(catering.getSpeisseangebot()).build(),

                            AttributeElement.builder("C-2").labelName("MenUebeschreibung")
                                    .actionType(AttributeElement.ActionType.NONE)
                                    .modificationType(AttributeElement.ModificationType.DIRECT)
                                    .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                    .mandatory(true).maxLength(300)
                                    .value(catering.getMenueBeschreibung()).build()
                    }
            );

        } else if (teilEvent.getElement().getType() == ClassType.ELEMENTLOCATION) {
            Location location = (Location) teilEvent.getElement();
            this.specialComponent.setAttributeElements(
                    new AttributeElement[]{
                        AttributeElement.builder("L-1").labelName("Adresse")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                .mandatory(true).maxLength(300)
                                .value(location.getAdresse()).build(),

                        AttributeElement.builder("L-2").labelName("Größe")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                .mandatory(true).maxLength(300)
                                .value(location.getGroesse()).build()

                    }
            );

        } else if (teilEvent.getElement().getType() == ClassType.ELEMENTMUSIK) {
            Musik musik = (Musik) teilEvent.getElement();
            this.specialComponent.setAttributeElements(
                    new AttributeElement[]{
                        AttributeElement.builder("M-1").labelName("KUenstler")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                .mandatory(true).maxLength(50)
                                .value(musik.getKuenstlername()).build(),

                        AttributeElement.builder("M-2").labelName("Genre")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                .mandatory(true).maxLength(100)
                                .value(musik.getGenre()).build()
                    }
            );

        } else if (teilEvent.getElement().getType() == ClassType.ELEMENTSONTIGES) {
            Sonstiges sonstiges = (Sonstiges) teilEvent.getElement();
            this.specialComponent.setAttributeElements(
                    new AttributeElement[] {
                        AttributeElement.builder("S-1").labelName("Zuständiger")
                                .actionType(AttributeElement.ActionType.NONE)
                                .modificationType(AttributeElement.ModificationType.DIRECT)
                                .allowedChars(AttributeElement.CHARSET_DEFAULT)
                                .mandatory(true).maxLength(200)
                                .value(sonstiges.getZustaendiger()).build()
                    }
            );
        }


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

    public void setController(GUIController controller) {
        this.controller = controller;
        saveButton.addObserver(controller);

    }


    public GUIController getController() {
        return controller;
    }

    public ButtonElement getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(ButtonElement saveButton) {
        this.saveButton = saveButton;
    }

    public AttributeComponent getAttributeComponent() {
        return attributeComponent;
    }

    public void setAttributeComponent(AttributeComponent attributeComponent) {
        this.attributeComponent = attributeComponent;
    }

    public Object getTeilEventIdent() {
        return teilEventIdent;
    }

    public AttributeComponent getSpecialComponent() {
        return specialComponent;
    }

    public void setSpecialComponent(AttributeComponent specialComponent) {
        this.specialComponent = specialComponent;
    }

    public AttributeElement[] getElements() {
        return elements;
    }

    public void setElements(AttributeElement[] elements) {
        this.elements = elements;
    }

    public AttributeComponent getKontaktInformation() {
        return kontaktInformation;
    }

    public void setKontaktInformation(AttributeComponent kontaktInformation) {
        this.kontaktInformation = kontaktInformation;
    }

    public void getEvent(EventUI event) {
        this.currentEventUI = event;
    }

    public EventUI getCurrentEventUI() {
        return currentEventUI;
    }

    public void setCurrentEventUI(EventUI currentEventUI) {
        this.currentEventUI = currentEventUI;
    }
}
