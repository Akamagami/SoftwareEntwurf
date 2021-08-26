package frontend.controller;

import de.dhbwka.swe.utils.event.*;
import frontend.pages.EventListGUI;

public class EventListGUIController extends GUIController {

    EventListGUI eventListGUI;
    public EventListGUIController(EventListGUI eventListGUI) {
        this.eventListGUI = eventListGUI;
    }


    public enum Commands implements EventCommand {
        ;

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

    }
}
