package frontend.UIData;

import backend.datenbasis.Speicher;
import backend.hilfsmittel.Hilfsmittel;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

import java.sql.Date;

public class HilfsmittelUI implements IDepictable {

    Hilfsmittel hilfsmittel;
    Integer currentAnzhal;
    Speicher speicher;
    Date start;
    Date ende;

    public HilfsmittelUI(Hilfsmittel hilfsmittel, Speicher speicher, Date start, Date ende) {
        this.hilfsmittel = hilfsmittel;
        this.speicher = speicher;
        this.start = start;
        this.ende = ende;
        currentAnzhal = speicher.getHiflsmittelUtils().getBestand(hilfsmittel.getId(), start, ende);
    }

    @Override
    public String toString() {
        return hilfsmittel.getName() + ", " + currentAnzhal + ", " + hilfsmittel.getBeschreibung();
    }

    @Override
    public String getElementID() {
        return null;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[0];
    }

    public Hilfsmittel getHilfsmittel() {
        return hilfsmittel;
    }

    public void setHilfsmittel(Hilfsmittel hilfsmittel) {
        this.hilfsmittel = hilfsmittel;
    }

    public Integer getCurrentAnzhal() {
        currentAnzhal = speicher.getHiflsmittelUtils().getBestand(hilfsmittel.getId(), start, ende);
        return currentAnzhal;
    }

    public void setCurrentAnzhal(Integer currentAnzhal) {
        this.currentAnzhal = currentAnzhal;
    }
}
