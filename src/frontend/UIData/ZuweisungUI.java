package frontend.UIData;

import backend.hilfsmittel.Zuweisung;
import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

public class ZuweisungUI implements IDepictable {

    Zuweisung zuweisung;

    public ZuweisungUI(Zuweisung zuweisung) {
        this.zuweisung = zuweisung;
    }

    @Override
    public String toString() {
        return zuweisung.getHilfsmittel().getName() + ", " + zuweisung.getMenge() +  ", "  + zuweisung.getHilfsmittel().getBeschreibung();
    }

    @Override
    public String getElementID() {
        return null;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[0];
    }

    public Zuweisung getZuweisung() {
        return zuweisung;
    }

    public void setZuweisung(Zuweisung zuweisung) {
        this.zuweisung = zuweisung;
    }
}
