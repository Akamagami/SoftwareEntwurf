package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.event.eventelement.Location;
import constants.Status;

public class ElementLocationManager implements EntityManager<Location> {
	
	private HashMap<String,Location> list = new HashMap<String,Location>();

	@Override
	public Optional<Location> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Location e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Location> getAll() {
		List<Location> ret = new ArrayList<Location>();
			for(Map.Entry<String, Location> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((String) params[0],(String) params[1],(double)  params[2],(String) params[3],(String) params[4]);
	}
}


