package frontend.UIData;

import backend.event.Event;
import constants.EventKategorie;
import constants.Status;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

import java.util.Date;
import java.util.UUID;

public class EventUI implements IDepictable {

    Event event;

    public EventUI(Event event) {
        this.event = event;
        /*
        this.attArr = new Attribute[Event.Attributes.values().length];
        boolean modifiable = true;
        String randID = UUID.randomUUID().toString();
        this.attArr[Event.Attributes.ID.ordinal()] = Event.Attributes.ID.createAttribute(this, this.id, randID);
        this.attArr[Event.Attributes.TITEL.ordinal()] = Event.Attributes.TITEL.createAttribute(this, this.titel, "Event");
        this.attArr[Event.Attributes.KUNDE.ordinal()] = Event.Attributes.KUNDE.createAttribute(this, this.kunde, "--");
        this.attArr[Event.Attributes.BESUCHER.ordinal()] = Event.Attributes.BESUCHER.createAttribute(this, this.besucher, "--");
        this.attArr[Event.Attributes.BESCHREIBUNG.ordinal()] = Event.Attributes.BESCHREIBUNG.createAttribute(this, this.beschreibung, "");
        this.attArr[Event.Attributes.START.ordinal()] = Event.Attributes.START.createAttribute(this, this.start, "--");
        this.attArr[Event.Attributes.ENDE.ordinal()] = Event.Attributes.ENDE.createAttribute(this, this.end, "--");
        this.attArr[Event.Attributes.BUDGET.ordinal()] = Event.Attributes.BUDGET.createAttribute(this, this.budget, "--");
        this.attArr[Event.Attributes.KATEGORIE.ordinal()] = Event.Attributes.KATEGORIE.createAttribute(this, this.kategorie, "--");
        this.attArr[Event.Attributes.STATUS.ordinal()] = Event.Attributes.STATUS.createAttribute(this, this.start, "--");
        */

    }

    @Override
    public String getElementID() {
        return event.getId();
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[0];
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return event.getTitel() + ", " + event.getKategorie().getDisplayName() + ", " + event.getStart() + ", " + event.getKunde() + ", " +
                event.getStatus().getDisplayName() + ", " + event.getBeschreibung();
    }
    /*
    public static enum Attributes {
        ID("ID", String.class, false, false, false),
        TITEL("Titel", String.class, true, true, true),
        KUNDE("Kunde", String.class, true, true, true),
        START("Start", Date.class, true, true, true),
        ENDE("Ende", Date.class, true, true, true),
        BESUCHER("Besucher", Integer.class, true, true, true),
        BESCHREIBUNG("Beschreibung", String.class, true, true, true),
        BUDGET("Budget", Double.class, true, true, true),
        KATEGORIE("Kategorie", EventKategorie.class, true, true, true),
        STATUS("Status", Status.class, true, true, true);

        private String name;
        private boolean visible;
        private boolean modifiable;
        private boolean editable;
        private Class clazz;

        private Attributes(String name, Class clazz, boolean visible, boolean modifyable, boolean editable) {
            this.name = name;
            this.visible = visible;
            this.clazz = clazz;
            this.modifiable = modifyable;
            this.editable = editable;
        }

        private Attribute createAttribute(Object dedicatedInstance, Object value, Object defaultValue) {
            return new Attribute(this.name, dedicatedInstance, this.clazz, value, defaultValue, this.visible, this.modifiable, this.editable);
        }
    }
    */

}
