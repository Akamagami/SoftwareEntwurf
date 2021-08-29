package frontend.UIData;

import backend.benutzer.Benutzer;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

public class MitarbeiterUI implements IDepictable {

    private Benutzer benutzer;

    public MitarbeiterUI(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Override
    public String toString() {
        String vorname = benutzer.fullName()[0];
        String nachname = benutzer.fullName()[1];

        if(benutzer.getKontaktInfo() != null) {
            return nachname + ", " + vorname + ", "+ benutzer.getRolle().getRollenName().getDisplayName()
                    + ", "+ benutzer.getKontaktInfo().getEmail() + ", " + benutzer.getKontaktInfo().getTele();
        }
        else {
            return nachname + ", " + vorname + ", "+ benutzer.getRolle().getRollenName().getDisplayName();
        }

    }

    @Override
    public String getElementID() {
        return null;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[0];
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
}
