package backend.benutzer;

import java.util.ArrayList;
import constants.Rollen;
public abstract class Rolle {
/* abstract Klasse für Rollen, 
 * Berechtigung in Liste vorhanden = true
 * */
	
	public ArrayList<Berechtigung> berechtigungList = new ArrayList<Berechtigung>();
	public Rollen rollenName;
	
	public boolean hatBerechtigung(Berechtigung be) {
		if(berechtigungList.contains(be)) {
			return true;
		} else {
			return false;
		}
	}
/*------------------------------------------------------------------------------*/
	public Rollen getRollenName() {
		return rollenName;
	}
	@Override
	public String toString() {
		return "Rolle [berechtigungList=" + berechtigungList + ", rollenName=" + rollenName + "]";
	}
	
}
