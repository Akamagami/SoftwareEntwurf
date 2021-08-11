package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.hilfsmittel.Zuweisung;
import constants.Status;

public class ZuweisungManager implements EntityManager<Zuweisung> {
	
	private HashMap<String,Zuweisung> list = new HashMap<String,Zuweisung>();

	@Override
	public Optional<Zuweisung> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Zuweisung e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Zuweisung> getAll() {
		List<Zuweisung> ret = new ArrayList<Zuweisung>();
			for(Map.Entry<String, Zuweisung> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((int) params[0]);
	}
}


