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

public class ElementFactory {
	
	private HashMap<ClassType,Integer> indexList = new HashMap<ClassType,Integer>();
	
	Rolle leer = new LeereRolle();

	public ElementFactory() {
		super();
		init();
	}
	
	private void init() {
		for(ClassType c: ClassType.values()) {
			indexList.put(c,1);
		}
	}
	
	public void updateIndexList(String[] index) {
		int cnt = 0;
		for(ClassType c: ClassType.values()) {
			indexList.put(c,Integer.parseInt(index[cnt]));
			cnt++;
		}
	}
	public Integer[] getIndexList() {
		Integer[] ret = new Integer[indexList.size()];
		int cnt = 0;
		
		for(ClassType c: ClassType.values()) {
			ret[cnt] = indexList.get(c);
		}
		return ret;
	}	
	private void upId(ClassType c) {
		indexList.put(c, indexList.get(c)+1);
	}
	
/*--------------------------------------------------------------------------------------------------------------------*/
	public Benutzer createBenutzer(Object[] params,Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.BENUTZER);
			upId(ClassType.BENUTZER);
		}
		Benutzer ret = new Benutzer((String) params[0],(String) params[1],newIndex);
		ret.setRolle(leer);
		return ret;
	}

	public Kontaktinformation createKontaktinformation(Object[] params,Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.KONTAKTINFORMATION);
			upId(ClassType.KONTAKTINFORMATION);
		}
		Kontaktinformation ret = new Kontaktinformation((String) params[0],(String) params[1],(String) params [2],newIndex);
		return ret;
	}

	public Gruppe createGruppe(Object[] params, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.GRUPPE);
			upId(ClassType.GRUPPE);
		}
		Gruppe ret = new Gruppe((String) params[0],newIndex);
		return ret;
	}
	
	public Object createBGruppe(Object[] params, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.GRUPPE);
			upId(ClassType.GRUPPE);
		}
		Beschaffungsgruppe ret = new Beschaffungsgruppe((String) params[0],newIndex);
		return ret;
	}

	public Object createEvent(Object[] params, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.EVENT);
			upId(ClassType.EVENT);
		}
		Event ret = new Event((String) params[0], (String) params[1],(Date) params[2],(Date) params[3],(int) params[4],(String) params[5],(double) params[6],(EventKategorie) params[7],newIndex);
		return ret;
	}

	public Object createTeilEvent(Object[] params, Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.TEILEVENT);
			upId(ClassType.TEILEVENT);
		}
		TeilEvent ret = new TeilEvent((Date) params[0],(Date) params[1],(String) params[2],newIndex);
		return ret;
	}

	public Object createHilfsmittel(Object[] params, Optional<String> optId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object createEventElement(Object[] params, Optional<String> optId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object createRequest(Object[] params, Optional<String> optId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object createZuweisung(Object[] params, Optional<String> optId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
