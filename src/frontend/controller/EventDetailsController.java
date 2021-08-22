package frontend.controller;

import frontend.EventCommand;
import frontend.GUIEvent;

public class EventDetailsController extends GUIController {

    public enum Commands implements EventCommand {
        ;

        private String cmdText;
        private Class<?> payloadType;
        private Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override public String getCommandText() { return null; }

        @Override public Class<?> getPayloadType() { return null; }
    }

    @Override
    public void processGUIEvent(GUIEvent ge) {
        super.processGUIEvent(ge);
    }
}
