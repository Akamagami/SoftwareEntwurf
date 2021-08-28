package frontend.controller;

import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Speicher;
import backend.event.Event;
import backend.event.TeilEvent;
import constants.ClassType;
import constants.EventKategorie;
import constants.Status;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import frontend.UIData.EventUI;
import frontend.UIData.TeilEventUI;
import frontend.pages.EventDetailsKosten;
import frontend.pages.EventDetailsTeilevent;
import frontend.pages.EventDetailsUebersicht;

import javax.swing.*;
import java.sql.Date;

public class EventDetailsController extends GUIController {

    private EventDetailsUebersicht Uebersicht;
    private EventDetailsTeilevent teilevent;
    private EventDetailsKosten kosten;
    private MainGUIController mainGUIController;
    private Speicher speicher;
    private EventUI currentEventUI;

    public EventDetailsController(EventDetailsUebersicht Uebersicht, EventDetailsTeilevent teilevent, EventDetailsKosten kosten, MainGUIController mainGUIController, Speicher speicher) {
        this.Uebersicht = Uebersicht;
        this.teilevent = teilevent;
        this.kosten = kosten;
        this.mainGUIController = mainGUIController;
        this.speicher = speicher;

    }


    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(ButtonElement.Commands.BUTTON_PRESSED)) {
            ButtonElement x = (ButtonElement) ge.getData();
            if (x.getID().equals("EDT-CRBTN")) {
                String[] string = {ClassType.ELEMENTCATERING.getDisplayName(), ClassType.ELEMENTLOCATION.getDisplayName(),
                        ClassType.ELEMENTMUSIK.getDisplayName(), ClassType.ELEMENTSONTIGES.getDisplayName()};
                JComboBox comboBox = new JComboBox(string);
                comboBox.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, comboBox, "Wählen Sie Eventelement-Typ", JOptionPane.QUESTION_MESSAGE);
                mainGUIController.getMainGUI().getTeilEventDetailsController().setCurrentTeilEventUI(null);
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.CREATE_TEILEVENT, comboBox.getSelectedItem()));
            }
            else if (x.getID().equals("SAFE-EDUE")) {
                if (currentEventUI == null) {
                    String[] params = Uebersicht.getAttributeComponent().getAttributeValuesAsArray();
                    String[] kontaktParams = Uebersicht.getKontaktBeschreibungComponent().getAttributeValuesAsArray();
                    for (EventKategorie kategorie : EventKategorie.values()
                    ) {
                        if (kategorie.getDisplayName().equals(params[7])) {
                            Object[] createData = {(String) params[0], (String) params[1], (Date) Date.valueOf(params[3]),
                                    (Date) Date.valueOf(params[4]), Integer.parseInt(params[5]), (String) params[2], (double) Double.parseDouble(params[6]),
                                    (EventKategorie) kategorie};

                            Event temp = (Event) speicher.createObject(ClassType.EVENT, createData);
                            this.currentEventUI = new EventUI(temp);
                            Object[] kontaktData = {kontaktParams[0], kontaktParams[1], kontaktParams[2]};

                            Kontaktinformation kontaktinformation = (Kontaktinformation) speicher.createObject(ClassType.KONTAKTINFORMATION, kontaktData);
                            temp.getKontaktInfoList().addKontaktInfo(kontaktinformation);
                        }

                    }
                    //mainGUIController.getMainGUI().getTeilEventDetailsController().getTeilEventDetailsUebersicht().setCurrentEventUI(null);
                    mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
                }
                else {
                    String[] params = Uebersicht.getAttributeComponent().getAttributeValuesAsArray();
                    String[] kontaktParams = Uebersicht.getKontaktBeschreibungComponent().getAttributeValuesAsArray();
                    for (EventKategorie kategorie : EventKategorie.values()
                    ) {
                        if (kategorie.getDisplayName().equals(params[7])) {

                            for (Status status1: Status.values()
                                 ) {
                                if (status1.getDisplayName().equals(params[8])){
                                    currentEventUI.getEvent().update((String) params[0], (String) params[1], (Date) Date.valueOf(params[3]),
                                    (Date) Date.valueOf(params[4]), Integer.parseInt(params[5]), (String) params[2], (double) Double.parseDouble(params[6]),
                                    (EventKategorie) kategorie, (Status) status1);
                                    Object[] kontaktData = {kontaktParams[0], kontaktParams[1], kontaktParams[2]};
                                    currentEventUI.getEvent().getKontaktInfoList().removeKontakt(0);
                                    Kontaktinformation kontaktinformation = (Kontaktinformation) speicher.createObject(ClassType.KONTAKTINFORMATION, kontaktData);
                                    currentEventUI.getEvent().getKontaktInfoList().addKontaktInfo(kontaktinformation);
                                }
                            }

                        }

                    }
                    mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
                }
            } else if (x.getID().equals("EDT-EDTBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent();
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.OPEN_EDIT_TEILEVENTPANE, simpleListComponent.getSelectedElement()));
            } else if (x.getID().equals("EDT-EDTBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() == null) {
                JOptionPane.showMessageDialog(null, "Bitte wähle ein Teilevent aus!");
            }
            else if (x.getID().equals("EDT-DELBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent();
                speicher.delete(ClassType.TEILEVENT, simpleListComponent.getSelectedElement().getElementID());
                TeilEventUI tempTeilEvent = (TeilEventUI) simpleListComponent.getSelectedElement();
                currentEventUI.getEvent().removeTeilEvent(tempTeilEvent.getTeilEvent());
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
            } else if (x.getID().equals("EDT-DELBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() == null) {
                JOptionPane.showMessageDialog(null, "Bitte wähle ein Teilevent aus!");
            }
        }
    }

    public void getEvent(EventUI event) {
        this.currentEventUI = event;
        Uebersicht.fillAttributes(event);
        mainGUIController.getMainGUI().getEventListGUIController().loadElements();
        mainGUIController.getMainGUI().getTeilEventDetailsController().getTeilEventDetailsUebersicht().getEvent(currentEventUI);
    }







    public enum Commands implements EventCommand {
        CREATE_TEILEVENT("CREATE_TEILEVENT", EventDetailsController.class),
        OPEN_EDIT_TEILEVENTPANE("OPEN_EDIT_TEILEVENT", EventDetailsController.class),
        RELOAD_PAGE("RELOAD_PAGE", EventDetailsController.class);

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




    public EventDetailsUebersicht getUebersicht() {
        return Uebersicht;
    }

    public void setUebersicht(EventDetailsUebersicht Uebersicht) {
        this.Uebersicht = Uebersicht;
    }

    public EventDetailsTeilevent getTeilevent() {
        return teilevent;
    }

    public void setTeilevent(EventDetailsTeilevent teilevent) {
        this.teilevent = teilevent;
    }

    public EventDetailsKosten getKosten() {
        return kosten;
    }

    public void setKosten(EventDetailsKosten kosten) {
        this.kosten = kosten;
    }

    public MainGUIController getMainGUIController() {
        return mainGUIController;
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }

    public EventUI getCurrentEventUI() {
        return currentEventUI;
    }

    public void setCurrentEventUI(EventUI currentEventUI) {
        this.currentEventUI = currentEventUI;
    }
}