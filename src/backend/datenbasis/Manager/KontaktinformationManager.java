package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.benutzer.Kontaktinformation;
import backend.datenbasis.EntityManager;

public class KontaktinformationManager implements EntityManager<Kontaktinformation> {
	
	private HashMap<String,Kontaktinformation> list = new HashMap<String,Kontaktinformation>();

	@Override
	public Optional<Kontaktinformation> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Kontaktinformation e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Kontaktinformation> getAll() {
		List<Kontaktinformation> ret = new ArrayList<Kontaktinformation>();
			for(Map.Entry<String, Kontaktinformation> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(String) params[2]);
	}
}
