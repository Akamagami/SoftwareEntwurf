package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.event.eventelement.Sonstiges;
import constants.Status;

public class ElementSonstigesManager implements EntityManager<Sonstiges> {
	
	private HashMap<String,Sonstiges> list = new HashMap<String,Sonstiges>();

	@Override
	public Optional<Sonstiges> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Sonstiges e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Sonstiges> getAll() {
		List<Sonstiges> ret = new ArrayList<Sonstiges>();
			for(Map.Entry<String, Sonstiges> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(double) params[2],(String) params[3]);
	}
}


