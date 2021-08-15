package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.event.TeilEvent;
import constants.Status;

public class TeilEventManager implements EntityManager<TeilEvent> {
	
	private HashMap<String,TeilEvent> list = new HashMap<String,TeilEvent>();

	@Override
	public Optional<TeilEvent> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(TeilEvent e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<TeilEvent> getAll() {
		List<TeilEvent> ret = new ArrayList<TeilEvent>();
			for(Map.Entry<String, TeilEvent> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((Date) params[0],(Date) params[1],(String) params[2],(Status) params[3]);
	}
}


