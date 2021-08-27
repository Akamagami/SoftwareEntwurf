package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import de.dhbwka.swe.utils.model.ImageElement;
import de.dhbwka.swe.utils.util.ImageLoader;
import frontend.controller.GUIController;


import javax.swing.*;

import java.awt.*;
import java.util.EventListener;

public class EventDetailsÜbersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement safeButton;
    private SlideshowComponent ssc;
    private AttributeComponent attributeComponent;
    private AttributeComponent kontaktBeschreibungComponent;


    public EventDetailsÜbersicht() {
        AttributeElement[] attributeElements = new AttributeElement[] {
                AttributeElement.builder("AE-1").labelName("Beschreibung")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(300).build(),

                AttributeElement.builder("AE-2").labelName("Kunde")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .mandatory(true).maxLength(100).toolTip("Vorname Nachname").build(),

                AttributeElement.builder("AE-3").labelName("Von")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_DATE)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build(),

                AttributeElement.builder("AE-4").labelName("Bis")
                .actionType(AttributeElement.ActionType.NONE)
                .modificationType(AttributeElement.ModificationType.DIRECT)
                .allowedChars(AttributeElement.CHARSET_DATE)
                .mandatory(true).maxLength(100).toolTip("DD.MM.YYYY").build()
        };

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


        ImageElement[] loadedImageElements = null;
        try {
            loadedImageElements = ImageLoader.loadImageElements("D:\\Programming\\Java\\SoftwareEntwurf\\src\\frontend\\images\\");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ssc = SlideshowComponent.builder("SSC").imageElements(loadedImageElements).smallImageSize(new Dimension(50, 50)).build();


        attributeComponent = AttributeComponent.builder("AC-1").attributeElements(attributeElements).title("Eventübersicht").build();
        kontaktBeschreibungComponent = AttributeComponent.builder("K-1").attributeElements(kontaktBeschreibung).title("Kontaktinformationen").build();
        safeButton = ButtonElement.builder("SAFE-EDUE").buttonText("Speichern").build();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(safeButton);

        this.setLayout(new GridLayout(2,2));
        //this.add(slider, BorderLayout.EAST);


        this.add(attributeComponent);
        this.add(kontaktBeschreibungComponent);
        this.add(ssc);
        this.add(buttonPanel);



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
    }


}
