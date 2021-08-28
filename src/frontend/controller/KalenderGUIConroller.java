package frontend.controller;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import frontend.pages.KalenderGUI;

public class KalenderGUIConroller extends GUIController {

    KalenderGUI kalenderGUI;

    public KalenderGUIConroller(KalenderGUI kalenderGUI) {
        this.kalenderGUI = kalenderGUI;
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

        @Override public Class<?> getPayloadType() {
            return null;
        }
    }

    @Override
    public void processGUIEvent(GUIEvent ge) {

    }
}
