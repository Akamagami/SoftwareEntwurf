package frontend.UIData;

import backend.event.TeilEvent;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

//Die Klasse TeilEventUI ermöglicht die Darstellung unserer eigenen Teilevents in den SimpleListComponents der SWE-Utils-Library
public class TeilEventUI implements IDepictable {

    private TeilEvent teilEvent;

    public TeilEventUI(TeilEvent teilEvent) {
        this.teilEvent = teilEvent;
    }

    @Override
    public String toString() {
        return teilEvent.getName() + ", " + teilEvent.getElement().getType().getDisplayName()
        + ", " + teilEvent.getStart() + ", " +  teilEvent.getEnde() + ", "
                + teilEvent.getElement().getBeschreibung();
    }


    @Override
    public String getElementID() {
        return null;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[0];
    }

    public TeilEvent getTeilEvent() {
        return teilEvent;
    }

    public void setTeilEvent(TeilEvent teilEvent) {
        this.teilEvent = teilEvent;
    }
}
