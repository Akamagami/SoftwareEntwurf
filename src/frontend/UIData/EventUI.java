package frontend.UIData;

import backend.event.Event;
import constants.EventKategorie;
import constants.Status;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

import java.util.Date;
import java.util.UUID;


//Die Klasse EventUI ermöglicht die Darstellung unserer eigenen Events in den SimpleListComponents der SWE-Utils-Library
public class EventUI implements IDepictable {

    Event event;

    public EventUI(Event event) {
        this.event = event;
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

}
