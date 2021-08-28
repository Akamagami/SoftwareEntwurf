package frontend.controller;

import backend.datenbasis.Speicher;
import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import frontend.pages.TeilEventDetailsHilfsmittel;
import frontend.pages.TeilEventDetailsMitarbeiter;
import frontend.pages.TeilEventDetailsÜbersicht;

public class TeilEventDetailsController extends GUIController {

    private TeilEventDetailsÜbersicht teilEventDetailsÜbersicht;
    private TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter;
    private TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel;
    private Speicher speicher;

    public TeilEventDetailsController(TeilEventDetailsÜbersicht teilEventDetailsÜbersicht, TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter,
                                      TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel, Speicher speicher) {
        this.teilEventDetailsÜbersicht = teilEventDetailsÜbersicht;
        this.teilEventDetailsMitarbeiter = teilEventDetailsMitarbeiter;
        this.teilEventDetailsHilfsmittel = teilEventDetailsHilfsmittel;
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
