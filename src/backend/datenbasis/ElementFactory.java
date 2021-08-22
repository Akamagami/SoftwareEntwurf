package backend.datenbasis;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Beschaffungsgruppe;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.benutzer.Rolle;
import backend.benutzer.rollen.LeereRolle;
import backend.event.Event;
import backend.event.TeilEvent;
import constants.ClassType;
import constants.EventKategorie;
import constants.Status;

public interface ElementFactory {
	
	public int getIndex();
	
	public void setIndex(String id);
	
	public Object create(Object[] params,Optional<String> optId);
		
}
