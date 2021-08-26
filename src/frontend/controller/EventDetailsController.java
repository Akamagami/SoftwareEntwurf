package frontend.controller;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import frontend.pages.EventDetailsKosten;
import frontend.pages.EventDetailsTeilevent;
import frontend.pages.EventDetailsÜbersicht;

import javax.swing.*;
import java.awt.*;

public class EventDetailsController extends GUIController {

    EventDetailsÜbersicht übersicht;
    EventDetailsTeilevent teilevent;
    EventDetailsKosten kosten;

    public EventDetailsController(EventDetailsÜbersicht übersicht, EventDetailsTeilevent teilevent, EventDetailsKosten kosten) {
        this.übersicht = übersicht;
        this.teilevent = teilevent;
        this.kosten = kosten;
    }

    public enum Commands implements EventCommand {
        BUTTON_PRESSED("ButtonElement.buttonPressed", ButtonElement.class),
        CHANGE_BACKGROUND_COLOR("CHANGE_BACKGROUND_COLOR", EventDetailsController.class);

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
        if (ge.getCmdText() == Commands.BUTTON_PRESSED.getCmdText()) {
            kosten.processUpdateEvent(new UpdateEvent(this, Commands.CHANGE_BACKGROUND_COLOR, null));
        }
    }


}
