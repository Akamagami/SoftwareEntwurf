package backend.datenbasis;

import backend.benutzer.Rolle;
import backend.benutzer.rollen.*;
import constants.Rollen;

public class RollenVerwaltung {
	/*
	 * Die Rollenverwaltung enthält die Rollen die Benutzern vergeben werden können
	 * In Zukunft könnten über diese Klasse auch Berechtigungen an bestimmte rollen zugewiesen werden
	 */
	private Rolle administrator = new Administrator();
	private Rolle beschaffungspersonal= new Beschaffungspersonal();
	private Rolle leereRolle= new LeereRolle();
	private Rolle montageleiter= new Montageleiter();
	private Rolle organisator= new Organisator();
	private Rolle personalmitarbeiter= new Personalmitarbeiter();
	public RollenVerwaltung() {
		super();
	}
	
	public Rolle getRolle(Rollen rolle) {
		switch(rolle) {
		case A: return administrator;
		case B:	return beschaffungspersonal;
		case M: return montageleiter;
		case N: return leereRolle;
		case O: return organisator;
		case P: return personalmitarbeiter;
		default:
			return leereRolle;
		}
		
	}
}
