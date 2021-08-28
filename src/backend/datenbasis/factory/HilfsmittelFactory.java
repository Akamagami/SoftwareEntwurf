package backend.datenbasis.factory;

import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.benutzer.Rolle;
import backend.benutzer.rollen.LeereRolle;
import backend.datenbasis.ElementFactory;
import backend.hilfsmittel.Hilfsmittel;
import constants.ClassType;

public class HilfsmittelFactory implements ElementFactory {

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
			if(Integer.parseInt(newIndex)+1 > index) {
				index = Integer.parseInt(newIndex)+1;
			}
		} else {
			newIndex = index+"";
			index++;
		}
		Hilfsmittel ret = new Hilfsmittel((String) params[0],(String) params[1],(int) params[2],newIndex);
		return ret;
	}

}
