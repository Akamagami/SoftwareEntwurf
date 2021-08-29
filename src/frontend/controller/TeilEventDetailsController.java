package frontend.controller;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Speicher;
import backend.event.EventElement;
import backend.event.TeilEvent;
import backend.event.eventelement.Catering;
import backend.event.eventelement.Location;
import backend.event.eventelement.Musik;
import backend.event.eventelement.Sonstiges;
import backend.hilfsmittel.Hilfsmittel;
import backend.hilfsmittel.Zuweisung;
import constants.ClassType;
import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.model.IDepictable;
import frontend.UIData.*;
import frontend.pages.*;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class TeilEventDetailsController extends GUIController {

    private TeilEventDetailsUebersicht teilEventDetailsUebersicht;
    private TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter;
    private TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel;
    private HilfsmittelDetails hilfsmittelDetails;
    private MitarbeiterDetails mitarbeiterDetails;
    private MainGUIController mainGUIController;
    private EventUI currentEventUI;
    private TeilEventUI currentTeilEventUI;
    private Speicher speicher;

    public TeilEventDetailsController(TeilEventDetailsUebersicht teilEventDetailsUebersicht, TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter,
                                      TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel, HilfsmittelDetails hilfsmittelDetails, MitarbeiterDetails mitarbeiterDetails, Speicher speicher, MainGUIController mainGUIController) {
        this.teilEventDetailsUebersicht = teilEventDetailsUebersicht;
        this.teilEventDetailsMitarbeiter = teilEventDetailsMitarbeiter;
        this.teilEventDetailsHilfsmittel = teilEventDetailsHilfsmittel;
        this.hilfsmittelDetails = hilfsmittelDetails;
        this.mitarbeiterDetails = mitarbeiterDetails;
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
            } else if (x.getID().equals("TEDM-ADDBTN")) {
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.ADD_MITARBEITER, currentTeilEventUI));

            } else if (x.getID().equals("MD-BTN")) {
                if (!currentTeilEventUI.getTeilEvent().hatGruppe()) {
                    Object[] params = {"Standardgruppe"};
                    currentTeilEventUI.getTeilEvent().addGruppe((Gruppe) speicher.createObject(ClassType.GRUPPE, params));
                }
                MitarbeiterUI mitarbeiterUI = (MitarbeiterUI) mitarbeiterDetails.getSimpleListComponent().getSelectedElement();
                currentTeilEventUI.getTeilEvent().addBenutzer(mitarbeiterUI.getBenutzer());

                mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));

            } else if (x.getID().equals("TEDM-DELBTN")) {
                MitarbeiterUI mitarbeiterUI = (MitarbeiterUI) teilEventDetailsMitarbeiter.getSimpleListComponent().getSelectedElement();
                currentTeilEventUI.getTeilEvent().removeBenutzer(mitarbeiterUI.getBenutzer());

                mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));

            } else if(x.getID().equals("TEDH-ADDBTN")) {
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.ADD_HILFSMITTEL, currentTeilEventUI));
            } else if (x.getID().equals("HD-BTN")) {
                String value1 = JOptionPane.showInputDialog("Bitte geben Sie eine Anzahl ein!");
                HilfsmittelUI hilfsmittelUI = (HilfsmittelUI) hilfsmittelDetails.getSimpleListComponent().getSelectedElement();
                int value = Integer.parseInt(value1);
                if (value <= hilfsmittelUI.getCurrentAnzhal() && value >0) {
                    Object[] params = {currentTeilEventUI.getTeilEvent(), hilfsmittelUI.getHilfsmittel(), value};
                    speicher.createObject(ClassType.ZUWEISUNG, params);
                } else {
                    JOptionPane.showMessageDialog(null, "Es sind nicht genug von diesen Hilfsmitteln vorhanden!");
                }

                mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));
            } else if (x.getID().equals("TEDH-DELBTN")) {
                ZuweisungUI zuweisungUI = (ZuweisungUI) teilEventDetailsHilfsmittel.getSimpleListComponent().getSelectedElement();
                speicher.delete(ClassType.ZUWEISUNG, zuweisungUI.getZuweisung().getId());

                mainGUIController.processGUIEvent(new GUIEvent(this, EventDetailsController.Commands.RELOAD_PAGE, null));
            }
        }
    }


    public void loadElements(){
        ArrayList<TeilEvent> teilEvents = mainGUIController.getMainGUI().getEventDetailsController().getCurrentEventUI().getEvent().getTeilEventList();
        mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().displayEvents(teilEvents);
        this.currentEventUI = mainGUIController.getMainGUI().getEventDetailsController().getCurrentEventUI();
    }

    public void loadMitarbeiter() {
        if (currentTeilEventUI.getTeilEvent().hatGruppe()) {
                teilEventDetailsMitarbeiter.displayMitarbeiter(currentTeilEventUI.getTeilEvent().getBenutzer());
            }
    }

    public void loadAllMitarbeiter() {
            mitarbeiterDetails.displayMitarbeiter((ArrayList<Benutzer>)(ArrayList<?>) speicher.getAll(ClassType.BENUTZER));
    }

    public void loadHilfsmittel() {
        ArrayList<Zuweisung> zuweisungArrayList = new ArrayList<>();
        for (Zuweisung zuweisung : (ArrayList<Zuweisung>)(ArrayList<?>) speicher.getAll(ClassType.ZUWEISUNG)
             ) {
            if (zuweisung.getTeilevent().getId().equals(currentTeilEventUI.getTeilEvent().getId())) {
                zuweisungArrayList.add(zuweisung);
            }
        }
        teilEventDetailsHilfsmittel.displayHilfsmittel(zuweisungArrayList);
    }

    public void loadAllHilfsmittel() {
        hilfsmittelDetails.displayHilfsmittel((ArrayList<Hilfsmittel>)(ArrayList<?>) speicher.getAll(ClassType.HILFSMITTEL), currentTeilEventUI, speicher);
    }


    public enum Commands implements EventCommand {
        ADD_MITARBEITER("ADD_MITARBEITER", TeilEventDetailsController.class),
        ADD_HILFSMITTEL("ADD_HILSFMITTEL", TeilEventDetailsController.class);

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
