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
	public boolean addMitarbeiter(Benutzer m) //mitarbeiter muss teil des bschaffungspersonals sein
	{
		if(m.getRolle().getRollenName() == Rollen.B ) {
			return super.addMitarbeiter(m);
		} else {
			return false;
		}	
	}
//------------------------------------------------------------------------------//	
	public boolean ernenneGruppenleiter(Benutzer m) { //gruppenleiter ernennen über objekt
		if(this.hatMitarbeiter(m)) {
			Gruppenleiter = m;
			return true;
		} else {
			return false;
		}
	}
	public boolean ernenneGruppenleiter(int index) {//gruppenleiter ernennen mit index
		if(index < this.getAnzahl()) {
			Gruppenleiter = this.getMitarbeiter(index);
			return true;
		} else {
			return false;
		}
	}
//------------------------------------------------------------------------------//	
	@Override
	public String toString() { //to string methode für den speichervorgang
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.BGRUPPE.getDisplayName() + d + super.toString() + ((Gruppenleiter == null) ? "" :  p + "Leiter" + d + Gruppenleiter.getId());
		return ret;
	}
	
}
