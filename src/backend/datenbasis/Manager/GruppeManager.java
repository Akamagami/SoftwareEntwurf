package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.benutzer.Gruppe;
import backend.datenbasis.EntityManager;

public class GruppeManager implements EntityManager<Gruppe> {
	
	private HashMap<String,Gruppe> list = new HashMap<String,Gruppe>();

	@Override
	public Optional<Gruppe> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Gruppe e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Gruppe> getAll() {
		List<Gruppe> ret = new ArrayList<Gruppe>();
			for(Map.Entry<String, Gruppe> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		
	}
}
