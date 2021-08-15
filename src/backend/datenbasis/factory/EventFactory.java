package backend.datenbasis.factory;

import java.util.Date;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.benutzer.Rolle;
import backend.benutzer.rollen.LeereRolle;
import backend.datenbasis.ElementFactory;
import backend.event.Event;
import constants.ClassType;
import constants.EventKategorie;

public class EventFactory implements ElementFactory {

	int index = 1;
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(String id) {
		index = Integer.parseInt(id);

	}

	@Override
	public Object create(Object[] params, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex = index+"";
			index++;
		}
		Event ret = new Event((String) params[0], (String) params[1],(Date) params[2],(Date) params[3],(int) params[4],(String) params[5],(double) params[6],(EventKategorie) params[7],newIndex);
		return ret;
	}

}
