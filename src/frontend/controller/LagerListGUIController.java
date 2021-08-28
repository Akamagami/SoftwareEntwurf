package frontend.controller;

import backend.datenbasis.Speicher;
import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import frontend.pages.LagerListGUI;

public class LagerListGUIController extends GUIController {

    private LagerListGUI lagerListGUI;
    private Speicher speicher;

    public LagerListGUIController(LagerListGUI lagerListGUI, Speicher speicher) {
        this.lagerListGUI = lagerListGUI;
        this.speicher = speicher;
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
