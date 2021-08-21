package backend.benutzer;

import java.util.ArrayList;

import constants.ClassType;
import constants.Rollen;

public class Beschaffungsgruppe extends Gruppe{
/*------------------------------------------------------------------------------//
 * Sind Teilevents uigeteilt, kann einen Gruppenleiter haben
 * Gruppenleiter wird auch als Mitarbeiter extra gelistet 
 * Nur Beschaffungspersonal
//------------------------------------------------------------------------------*/
	
	
//------------------------------------------------------------------------------//
	
	
	private Benutzer Gruppenleiter;
	
	public Beschaffungsgruppe(String name,String id) {
		super(name,id);
	}
	public Benutzer getGruppenleiter() {
		return Gruppenleiter;
	}
//------------------------------------------------------------------------------//
	@Override
	public boolean addMitarbeiter(Benutzer m)
	{
		if(m.getRolle().getRollenName() == Rollen.B ) {
			return super.addMitarbeiter(m);
		} else {
			return false;
		}	
	}
//------------------------------------------------------------------------------//	
	public boolean ernenneGruppenleiter(Benutzer m) {
		if(this.hatMitarbeiter(m)) {
			Gruppenleiter = m;
			return true;
		} else {
			return false;
		}
	}
	public boolean ernenneGruppenleiter(int index) {
		if(index < this.getAnzahl()) {
			Gruppenleiter = this.getMitarbeiter(index);
			return true;
		} else {
			return false;
		}
	}
//------------------------------------------------------------------------------//	
	@Override
	public String toString() {
		String d = ",";
		String p = d + "|" + d;
		
		String ret = ClassType.BGRUPPE + super.toString() + ((Gruppenleiter == null) ? "" :  p + Gruppenleiter.getId());
		return ret;
	}
	
}
