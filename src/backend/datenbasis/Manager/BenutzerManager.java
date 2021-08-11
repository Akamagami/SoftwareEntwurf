package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.datenbasis.EntityManager;

public class BenutzerManager implements EntityManager<Benutzer> {

	private HashMap<String,Benutzer> list = new HashMap<String,Benutzer>();
	
	@Override
	public Optional<Benutzer> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Benutzer e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Benutzer> getAll() {
		List<Benutzer> ret = new ArrayList<Benutzer>();
			for(Map.Entry<String, Benutzer> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, String[] params) {
		// nothing		
	}

}
