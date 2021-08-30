package frontend.controller;

import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Speicher;
import backend.event.Event;
import constants.ClassType;
import constants.EventKategorie;
import constants.Status;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.gui.SimpleListComponent;
import frontend.UIData.EventUI;
import frontend.UIData.TeilEventUI;
import frontend.pages.EventDetailsTeilevent;
import frontend.pages.EventDetailsUebersicht;

import javax.swing.*;
import java.sql.Date;

public class EventDetailsController extends GUIController {

    private EventDetailsUebersicht Uebersicht;
    private EventDetailsTeilevent teilevent;
    private MainGUIController mainGUIController;
    private Speicher speicher;
    private EventUI currentEventUI; //Auf EventListGUI ausgewähltes Event im SimpleListComponent

    //Der EventDetailsController verwaltet die Eingaben aller SWE-Utils-Components der Seiten EventDetailsUebersicht und EventDetailsTeilevent

    public EventDetailsController(EventDetailsUebersicht Uebersicht, EventDetailsTeilevent teilevent, MainGUIController mainGUIController, Speicher speicher) {
        this.Uebersicht = Uebersicht;
        this.teilevent = teilevent;
        this.mainGUIController = mainGUIController;
        this.speicher = speicher;
    }


    //In der folgenden Funktion werden die GUIEvents von den vom Controller verwalteten Seiten ausgewertet und je nach Component, der ein Event gesendet hat,
    // das Event weiter verarbeitet
    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(ButtonElement.Commands.BUTTON_PRESSED)) {
            ButtonElement x = (ButtonElement) ge.getData();
            if (x.getID().equals("EDT-CRBTN")) {
                //Erstellen einer Auswahl für die Kategorie des Teilevents
                String[] string = {ClassType.ELEMENTCATERING.getDisplayName(), ClassType.ELEMENTLOCATION.getDisplayName(),
                        ClassType.ELEMENTMUSIK.getDisplayName(), ClassType.ELEMENTSONTIGES.getDisplayName()};
                JComboBox comboBox = new JComboBox(string);
                comboBox.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, comboBox, "Waehlen Sie Eventelement-Typ", JOptionPane.QUESTION_MESSAGE);

                //CurrentTeilEventUI wird zurückgesetzt, damit nicht ein neues oder zuvor bearbeitetes Teilevent erzeugt/bearbeitet wird, sondern
                //das aktuelle Teilevent bearbeitet wird
                mainGUIController.getMainGUI().getTeilEventDetailsController().setCurrentTeilEventUI(null);

                //MainGUIController wird augefordert das TeileventPane in einem neuen Fenster aufzurufen. Als Payload wird die in der Auswahl gewählte Kategorie
                //mitgegeben
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.CREATE_TEILEVENT, comboBox.getSelectedItem()));
            }
            else if (x.getID().equals("SAFE-EDUE")) {

                //Überprüfung ob ein neues Event erstellt wird oder ein ausgewähltes bearbeitet werden soll. CurrentEventUI ist das im SimpleListComponent ausgewählte Event
                if (currentEventUI == null) {
                    //Erzeugung eines neuen Events
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
                            refreshTeilevents(currentEventUI);
                            Object[] kontaktData = {kontaktParams[0], kontaktParams[1], kontaktParams[2]};

                            Kontaktinformation kontaktinformation = (Kontaktinformation) speicher.createObject(ClassType.KONTAKTINFORMATION, kontaktData);
                            temp.getKontaktInfoList().addKontaktInfo(kontaktinformation);
                        }

                    }

                    //Befehl an den MainGUIController die Seiten der Anwendung zu aktualisieren
                    mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
                }
                else {

                    //Auslesen der AttributeComponents
                    String[] params = Uebersicht.getAttributeComponent().getAttributeValuesAsArray();
                    String[] kontaktParams = Uebersicht.getKontaktBeschreibungComponent().getAttributeValuesAsArray();
                    for (EventKategorie kategorie : EventKategorie.values()
                    ) {
                        if (kategorie.getDisplayName().equals(params[7])) {

                            for (Status status1: Status.values()
                                 ) {
                                if (status1.getDisplayName().equals(params[8])){
                                    //Update-Funktion von Events wird aufgerufen. Neue Werte eingetragen
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
                    //Befehl an den MainGUIController die Seiten der Anwendung zu aktualisieren
                    mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));
                }
                //Wird der Edit-Button betätigt wird der Wert SimpleListComponent.getSelectedItem() abgefragt und an den MainGUIController weitergegeben.
                //Dieser öffnet das Editier-Fenster für die TeilEvents
            } else if (x.getID().equals("EDT-EDTBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent();
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.OPEN_EDIT_TEILEVENTPANE, simpleListComponent.getSelectedElement()));

                //Debug, dass man ein TeilEvent vor dem Editieren auswählen muss
            } else if (x.getID().equals("EDT-EDTBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() == null) {
                JOptionPane.showMessageDialog(null, "Bitte waehle ein Teilevent aus!");
            }
            //Löschen eines TeilEvents im Speicher als auch im Event selbst
            else if (x.getID().equals("EDT-DELBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() != null) {
                SimpleListComponent simpleListComponent = mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent();
                speicher.delete(ClassType.TEILEVENT, simpleListComponent.getSelectedElement().getElementID());
                TeilEventUI tempTeilEvent = (TeilEventUI) simpleListComponent.getSelectedElement();
                currentEventUI.getEvent().removeTeilEvent(tempTeilEvent.getTeilEvent());
                mainGUIController.processGUIEvent(new GUIEvent(this, Commands.RELOAD_PAGE, null));

                //Debug, dass man ein TeilEvent vor dem Löschen auswählen muss
            } else if (x.getID().equals("EDT-DELBTN") && mainGUIController.getMainGUI().getEventDetailsController().getTeilevent().getSimpleListComponent().getSelectedElement() == null) {
                JOptionPane.showMessageDialog(null, "Bitte waehle ein Teilevent aus!");
            }
        }
    }

    //Funktion wird in MainGUIController aufgerufen. Dient zur Übertragung des aktuellen Events an andere Controller und Pages, die darauf keinen direkten Zugriff haben
    //Event wird von MainGUIController als Payload erhalten und per Aufruf weitergegeben
    //Nach erfolgreicher Übergabe werden bei einem Edit-Aufruf des Events ebenfalls die Funktion fillAttributes aufgerufen und die Elemente in die SimpleListComponents geladen
    public void getEvent(EventUI event) {
        this.currentEventUI = event;
        Uebersicht.fillAttributes(event);
        mainGUIController.getMainGUI().getEventListGUIController().loadElements();
        mainGUIController.getMainGUI().getTeilEventDetailsController().getTeilEventDetailsUebersicht().getEvent(currentEventUI);
    }

    //Erneuert die CurrentEventUI im TeilEventDetailsController
    public void refreshTeilevents(EventUI event) {
   	 mainGUIController.getMainGUI().getTeilEventDetailsController().setCurrentEventUI(event);
   	 mainGUIController.getMainGUI().getTeilEventDetailsController().loadElements();
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

    public EventDetailsTeilevent getTeilevent() {
        return teilevent;
    }

    public void setTeilevent(EventDetailsTeilevent teilevent) {
        this.teilevent = teilevent;
    }

    public EventUI getCurrentEventUI() {
        return currentEventUI;
    }

    public void setCurrentEventUI(EventUI currentEventUI) {
        this.currentEventUI = currentEventUI;
    }
}