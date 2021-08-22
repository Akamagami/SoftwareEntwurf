package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.event.eventelement.Musik;
import constants.Status;

public class ElementMusikManager implements EntityManager<Musik> {
	
	private HashMap<String,Musik> list = new HashMap<String,Musik>();

	@Override
	public Optional<Musik> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Musik e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Musik> getAll() {
		List<Musik> ret = new ArrayList<Musik>();
			for(Map.Entry<String, Musik> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(double)  params[2],(String) params[3],(String) params[4]);
	}
}


