package backend.datenbasis.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import backend.datenbasis.EntityManager;
import backend.hilfsmittel.Request;
import constants.Status;

public class RequestManager implements EntityManager<Request> {
	
	private HashMap<String,Request> list = new HashMap<String,Request>();

	@Override
	public Optional<Request> get(String id) {
		return Optional.of(list.get(id));
	}

	@Override
	public void delete(String id) {
		list.remove(id);
		
	}

	@Override
	public void save(Request e) {
		list.put(e.getId(), e);
		
	}

	@Override
	public List<Request> getAll() {
		List<Request> ret = new ArrayList<Request>();
			for(Map.Entry<String, Request> entry : list.entrySet()) {
				ret.add(entry.getValue());
			}
		return ret;
	}

	@Override
	public void update(String id, Object[] params) {
		list.get(id).update((int) params[0]);
	}
}


