package frontend.controller;

import backend.datenbasis.Speicher;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import execution.Main;
import frontend.MainGUI;
import frontend.pages.EventListGUI;
import frontend.panes.EventDetailsPane;

import javax.swing.*;

public class EventListGUIController extends GUIController {

    private EventListGUI eventListGUI;
    private MainGUIController mainGUIController;
    private Speicher speicher;

    public EventListGUIController(EventListGUI eventListGUI, MainGUIController mainGUIController,Speicher speicher) {
        this.eventListGUI = eventListGUI;
        this.mainGUIController = mainGUIController;
        this.speicher = speicher;
    }


    public enum Commands implements EventCommand {
        OPEN_EVENTPANE("CreateEventButton.pressed", EventDetailsController.class);

        private String cmdText;
        private Class<?> payloadType;
        private Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }


        @Override
        public String getCmdText() {
            return null;
        }

        @Override
        public Class<?> getPayloadType() {
            return null;
        }
    }

    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(ButtonElement.Commands.BUTTON_PRESSED)) {
            ButtonElement x = (ButtonElement) ge.getSource();
            if (x.getID().equals("BTN-CREATE-EVENT")) {
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.OPEN_EVENTPANE, null));
            }
        }
    }
}
