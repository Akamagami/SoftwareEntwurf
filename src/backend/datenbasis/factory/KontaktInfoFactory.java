package backend.datenbasis.factory;

import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Kontaktinformation;
import backend.benutzer.Rolle;
import backend.benutzer.rollen.LeereRolle;
import backend.datenbasis.ElementFactory;
import constants.ClassType;

public class KontaktInfoFactory implements ElementFactory {

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
			index = Integer.parseInt(optId.get())+1;
		} else {
			newIndex = index+"";
			index++;
		}
		Kontaktinformation ret = new Kontaktinformation((String) params[0],(String) params[1],(String) params [2],newIndex);
		return ret;
	}

}
