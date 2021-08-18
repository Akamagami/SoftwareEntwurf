package backend.datenbasis.factory;

import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.benutzer.Rolle;
import backend.benutzer.rollen.LeereRolle;
import backend.datenbasis.ElementFactory;
import backend.event.EventElement;
import backend.event.eventelement.Catering;
import backend.event.eventelement.Sonstiges;
import constants.ClassType;


public class ElementCateringFactory implements ElementFactory {

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
		Catering ret = new Catering((String) params[0],(String) params[1],(double) params[2],(String) params[3],(String) params[4],newIndex);
		return ret;
	}

}
