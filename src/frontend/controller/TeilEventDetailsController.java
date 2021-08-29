package frontend.controller;

import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Speicher;
import backend.event.EventElement;
import backend.event.TeilEvent;
import backend.event.eventelement.Catering;
import backend.event.eventelement.Location;
import backend.event.eventelement.Musik;
import backend.event.eventelement.Sonstiges;
import constants.ClassType;
import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.gui.ButtonElement;
import frontend.UIData.EventUI;
import frontend.UIData.TeilEventUI;
import frontend.pages.TeilEventDetailsHilfsmittel;
import frontend.pages.TeilEventDetailsMitarbeiter;
import frontend.pages.TeilEventDetailsUebersicht;
import java.sql.Date;
import java.util.ArrayList;

public class TeilEventDetailsController extends GUIController {

    private TeilEventDetailsUebersicht teilEventDetailsUebersicht;
    private TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter;
    private TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel;
    private MainGUIController mainGUIController;
    private EventUI currentEventUI;
    private TeilEventUI currentTeilEventUI;
    private Speicher speicher;

    public TeilEventDetailsController(TeilEventDetailsUebersicht teilEventDetailsUebersicht, TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter,
                                      TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel, Speicher speicher, MainGUIController mainGUIController) {
        this.teilEventDetailsUebersicht = teilEventDetailsUebersicht;
        this.teilEventDetailsMitarbeiter = teilEventDetailsMitarbeiter;
        this.teilEventDetailsHilfsmittel = teilEventDetailsHilfsmittel;
        this.mainGUIController = mainGUIController;
        this.speicher = speicher;
    }


    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(ButtonElement.Commands.BUTTON_PRESSED)) {
            ButtonElement x = (ButtonElement) ge.getSource();
            if (x.getID().equals("TED-SAVE-BUTTON")) {
                if (currentTeilEventUI == null ){
                    String[] params = teilEventDetailsUebersicht.getAttributeComponent().getAttributeValuesAsArray();
                    String[] specialParams = teilEventDetailsUebersicht.getSpecialComponent().getAttributeValuesAsArray();
                    String[] kontaktParams = teilEventDetailsUebersicht.getKontaktInformation().getAttributeValuesAsArray();

                    EventElement eventElement = null;

                    Object teilEventIdent = teilEventDetailsUebersicht.getTeilEventIdent();
                    if (teilEventIdent == "Catering") {
                        Object[] eventelement = {params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]} ;
                        eventElement  = (EventElement) speicher.createObject(ClassType.ELEMENTCATERING, eventelement);

                    } else if (teilEventIdent == "Location") {
                        Object[] eventelement = {params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]} ;
                        eventElement  = (EventElement) speicher.createObject(ClassType.ELEMENTLOCATION, eventelement);


                    }else if (teilEventIdent == "Musik") {
                        Object[] eventelement = {params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]} ;
                        eventElement  = (EventElement) speicher.createObject(ClassType.ELEMENTMUSIK, eventelement);


                    } else if (teilEventIdent == "Sonstiges") {
                        Object[] eventelement = {params[0], params[1], Double.parseDouble(params[4]) , specialParams[0]} ;
                        eventElement  = (EventElement) speicher.createObject(ClassType.ELEMENTSONTIGES, eventelement);

                    }
                    Object[] kontaktData = {kontaktParams[0], kontaktParams[1], kontaktParams[2]};

                    Kontaktinformation kontaktinformation = (Kontaktinformation) speicher.createObject(ClassType.KONTAKTINFORMATION, kontaktData);
                    eventElement.getKontaktInfoList().addKontaktInfo(kontaktinformation);

                    Object[] teilEventData = {Date.valueOf(params[2]), Date.valueOf(params[3]), params[0]};
                    TeilEvent teilEvent = (TeilEvent) speicher.createObject(ClassType.TEILEVENT, teilEventData);
                    currentTeilEventUI = new TeilEventUI(teilEvent);
                    teilEvent.addEventElement(eventElement);
                    teilEventDetailsUebersicht.getCurrentEventUI().getEvent().addTeilEvent(teilEvent);

                    mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));

                } else {
                    String[] params = teilEventDetailsUebersicht.getAttributeComponent().getAttributeValuesAsArray();
                    String[] specialParams = teilEventDetailsUebersicht.getSpecialComponent().getAttributeValuesAsArray();
                    String[] kontaktParams = teilEventDetailsUebersicht.getKontaktInformation().getAttributeValuesAsArray();

                    currentTeilEventUI.getTeilEvent().update(Date.valueOf(params[2]), Date.valueOf(params[3]), params[0]);
                    Object type = currentTeilEventUI.getTeilEvent().getElement().getType();

                    if (type == ClassType.ELEMENTCATERING) {
                        Catering catering = (Catering) currentTeilEventUI.getTeilEvent().getElement();
                        catering.update(params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]);

                    } else if (type == ClassType.ELEMENTLOCATION) {
                        Location location = (Location) currentTeilEventUI.getTeilEvent().getElement();
                        location.update(params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]);

                    } else if (type == ClassType.ELEMENTMUSIK) {
                        Musik musik = (Musik) currentTeilEventUI.getTeilEvent().getElement();
                        musik.update(params[0], params[1], Double.parseDouble(params[4]) , specialParams[0], specialParams[1]);

                    } else if (type == ClassType.ELEMENTSONTIGES) {
                        Sonstiges sonstiges = (Sonstiges) currentTeilEventUI.getTeilEvent().getElement();
                        sonstiges.update(params[0], params[1], Double.parseDouble(params[4]) , specialParams[0]);

                    }
                    Object[] kontaktData = {kontaktParams[0], kontaktParams[1], kontaktParams[2]};
                    currentEventUI.getEvent().getKontaktInfoList().removeKontakt(0);
                    Kontaktinformation kontaktinformation = (Kontaktinformation) speicher.createObject(ClassType.KONTAKTINFORMATION, kontaktData);
                    currentEventUI.getEvent().getKontaktInfoList().addKontaktInfo(kontaktinformation);

                    mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));
                }
            }
        }
    }


    public void loadElements(){
        ArrayList<TeilEvent> teilEvents = teilEventDetailsUebersicht.getCurrentEventUI().getEvent().getTeilEventList();
        mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().displayEvents(teilEvents);
        this.currentEventUI = mainGUIController.getMainGUI().getEventDetailsController().getCurrentEventUI();
    }


    public enum Commands implements EventCommand {
        CREATE_TEILEVENT("RELOAD_PAGE", EventDetailsController.class);

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

        @Override
        public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }


    public void setCurrentTeilEventUI(TeilEventUI currentTeilEventUI) {
        this.currentTeilEventUI = currentTeilEventUI;
    }

    public TeilEventDetailsUebersicht getTeilEventDetailsUebersicht() {
        return teilEventDetailsUebersicht;
    }

    public void setTeilEventDetailsUebersicht(TeilEventDetailsUebersicht teilEventDetailsUebersicht) {
        this.teilEventDetailsUebersicht = teilEventDetailsUebersicht;
    }

    public TeilEventDetailsMitarbeiter getTeilEventDetailsMitarbeiter() {
        return teilEventDetailsMitarbeiter;
    }

    public void setTeilEventDetailsMitarbeiter(TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter) {
        this.teilEventDetailsMitarbeiter = teilEventDetailsMitarbeiter;
    }

    public TeilEventDetailsHilfsmittel getTeilEventDetailsHilfsmittel() {
        return teilEventDetailsHilfsmittel;
    }

    public void setTeilEventDetailsHilfsmittel(TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel) {
        this.teilEventDetailsHilfsmittel = teilEventDetailsHilfsmittel;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }

    public MainGUIController getMainGUIController() {
        return mainGUIController;
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public EventUI getCurrentEventUI() {
        return currentEventUI;
    }

    public void setCurrentEventUI(EventUI currentEventUI) {
        this.currentEventUI = currentEventUI;
    }

    public TeilEventUI getCurrentTeilEventUI() {
        return currentTeilEventUI;
    }

}
