package frontend.controller;

import backend.datenbasis.Speicher;
import de.dhbwka.swe.utils.event.*;
import frontend.pages.HilfsmittelDetails;

public class HilfsmittelDetailsController extends GUIController {

    HilfsmittelDetails hilfsmittelDetails;
    private Speicher speicher;

    public HilfsmittelDetailsController(HilfsmittelDetails hilfsmittelDetails, Speicher speicher) {
        this.hilfsmittelDetails = hilfsmittelDetails;
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
