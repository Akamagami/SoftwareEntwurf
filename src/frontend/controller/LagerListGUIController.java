package frontend.controller;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import frontend.pages.LagerListGUI;

public class LagerListGUIController extends GUIController {

    private LagerListGUI lagerListGUI;

    public LagerListGUIController(LagerListGUI lagerListGUI) {
        this.lagerListGUI = lagerListGUI;
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
