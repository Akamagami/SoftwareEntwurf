package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.event.Event;
import constants.EventKategorie;
import constants.Status;
import backend.datenbasis.EntityManager;

public class EventManager implements EntityManager<Event> {
	
	private HashMap<String,Event> list = new HashMap<String,Event>();

	@Override
	public Optional<Event> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Event e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Event> getAll() {
		List<Event> ret = new ArrayList<Event>();
			for(Map.Entry<String, Event> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0], (String) params[1],(Date) params[2],(Date) params[3],(int) params[4],(String) params[5],(double) params[6],(EventKategorie) params[7],(Status) params[8]);
	}
}
