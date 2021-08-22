package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.hilfsmittel.Hilfsmittel;
import constants.Status;

public class HilfsmittelManager implements EntityManager<Hilfsmittel> {
	
	private HashMap<String,Hilfsmittel> list = new HashMap<String,Hilfsmittel>();

	@Override
	public Optional<Hilfsmittel> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Hilfsmittel e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Hilfsmittel> getAll() {
		List<Hilfsmittel> ret = new ArrayList<Hilfsmittel>();
			for(Map.Entry<String, Hilfsmittel> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(int) Integer.parseInt((String) params[2]));
	}
}


