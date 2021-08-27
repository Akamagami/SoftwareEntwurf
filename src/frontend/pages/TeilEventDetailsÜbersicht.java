package frontend.pages;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.*;
import frontend.controller.GUIController;
import frontend.controller.MainGUIController;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public class TeilEventDetailsÜbersicht extends JPanel implements IUpdateEventListener, IGUIEventSender {

    private GUIController controller;
    private ButtonElement returnButton;
    private AttributeComponent attributeComponent;

    public TeilEventDetailsÜbersicht() {

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

        attributeComponent = AttributeComponent.builder("AC-1").attributeElements(attributeElements).title("Eventübersicht").build();

        this.setLayout(new GridLayout(2,2));
        this.add(attributeComponent);
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
