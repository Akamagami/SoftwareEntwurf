package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.event.EventElement;
import constants.Status;

public class EventElementManager implements EntityManager<EventElement> {
	
	private HashMap<String,EventElement> list = new HashMap<String,EventElement>();

	@Override
	public Optional<EventElement> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(EventElement e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<EventElement> getAll() {
		List<EventElement> ret = new ArrayList<EventElement>();
			for(Map.Entry<String, EventElement> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(double) params[2]);
	}
}


