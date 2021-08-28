package frontend.controller;

import backend.datenbasis.Speicher;
import backend.event.Event;
import constants.ClassType;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import execution.Main;
import frontend.MainGUI;
import frontend.pages.EventListGUI;
import frontend.panes.EventDetailsPane;

import javax.swing.*;
import java.util.ArrayList;

public class EventListGUIController extends GUIController {

    private EventListGUI eventListGUI;
    private MainGUIController mainGUIController;
    private Speicher speicher;



    public EventListGUIController(EventListGUI eventListGUI, MainGUIController mainGUIController, Speicher speicher) {
        this.eventListGUI = eventListGUI;
        this.mainGUIController = mainGUIController;
        this.speicher = speicher;

        loadElements();
    }


    public enum Commands implements EventCommand {
        OPEN_EVENTPANE("CreateEventButton.pressed", EventDetailsController.class),
        OPEN_EDIT_EVENTPANE("EditEventButton.pressed", EventDetailsController.class),
        RELOAD_PAGE("RELOAD_PAGE", EventDetailsController.class);

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
                mainGUIController.getMainGUI().getEventDetailsController().setCurrentEventUI(null);
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.OPEN_EVENTPANE, null));

            }
            else if (x.getID().equals("BTN-EDIT-EVENT") && this.eventListGUI.getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = eventListGUI.getSimpleListComponent();
                mainGUIController.getMainGUI().getEventDetailsController().setCurrentEventUI(null);
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.OPEN_EDIT_EVENTPANE, simpleListComponent.getSelectedElement()));
            }
            else if (x.getID().equals("BTN-EDIT-EVENT") && this.eventListGUI.getSimpleListComponent().getSelectedElement() == null) {
                JOptionPane.showMessageDialog(null, "Wählen Sie ein Event aus!");
            }
            else if (x.getID().equals("BTN-DELETE-EVENT") && this.eventListGUI.getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = eventListGUI.getSimpleListComponent();

                speicher.delete(ClassType.EVENT, simpleListComponent.getSelectedElement().getElementID());
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
            }
        }
    }

    public void loadElements() {
        ArrayList<Event> events = (ArrayList<Event>)(ArrayList<?>) speicher.getAll(ClassType.EVENT);
        eventListGUI.displayEvents(events);
    }

    public EventListGUI getEventListGUI() {
        return eventListGUI;
    }

    public void setEventListGUI(EventListGUI eventListGUI) {
        this.eventListGUI = eventListGUI;
    }

    public MainGUIController getMainGUIController() {
        return mainGUIController;
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }

}
