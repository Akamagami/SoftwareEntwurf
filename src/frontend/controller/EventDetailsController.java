package frontend.controller;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import frontend.pages.EventDetailsKosten;
import frontend.pages.EventDetailsTeilevent;
import frontend.pages.EventDetailsÜbersicht;

import javax.swing.*;
import java.awt.*;

public class EventDetailsController extends GUIController {

    private EventDetailsÜbersicht übersicht;
    private EventDetailsTeilevent teilevent;
    private EventDetailsKosten kosten;
    private MainGUIController mainGUIController;

    public EventDetailsController(EventDetailsÜbersicht übersicht, EventDetailsTeilevent teilevent, EventDetailsKosten kosten, MainGUIController mainGUIController) {
        this.übersicht = übersicht;
        this.teilevent = teilevent;
        this.kosten = kosten;
        this.mainGUIController = mainGUIController;
    }

    public enum Commands implements EventCommand {
        CREATE_TEILEVENT("CREATE_TEILEVENT", EventDetailsController.class);

        private String cmdText;
        private Class<?> payloadType;
        private Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }


        @Override
        public String getCmdText() {
            return this.cmdText;
        }

        @Override public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }


    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(ButtonElement.Commands.BUTTON_PRESSED)) {
            ButtonElement x = (ButtonElement) ge.getData();
            if (x.getID().equals("EDT-CRBTN")) {
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.CREATE_TEILEVENT, null));
            }
        }
    }


}
