package backend.benutzer;

import java.util.ArrayList;

public abstract class Rolle {
/* abstract Klasse für Rollen, 
 * Berechtigung in Liste vorhanden = true
 * */
	private ArrayList<Berechtigung> berechtigungList = new ArrayList<Berechtigung>();
	
	public boolean hatBerechtigung(Berechtigung be) {
		if(berechtigungList.contains(be)) {
			return true;
		} else {
			return false;
		}
	}
}
