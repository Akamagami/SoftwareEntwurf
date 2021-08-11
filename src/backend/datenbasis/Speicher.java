package backend.datenbasis;

import java.util.HashMap;
import java.util.List;

import backend.benutzer.Benutzer;
import backend.datenbasis.Manager.BenutzerManager;
import constants.ClassType;

public class Speicher<E> {
	ElementFactory creator = new ElementFactory();
	
	private HashMap<ClassType,EntityManager> managers = new HashMap<ClassType,EntityManager>();
	

	public Speicher() {
		super();
	}
	
	private void init() {
		EntityManager<Benutzer> userManager = new BenutzerManager();
		managers.put(ClassType.BENUTZER,userManager);
	}
	
	public void save(ClassType type, Object o) {
		managers.get(type).save(o);
	}
	public void update(ClassType type, String id,String[] params) {
		managers.get(type).update(id,params);
	}
	public Object getObject(ClassType type, String id) {
		return managers.get(type).get(id);
	}
	public List<Object> getAll(ClassType type, String id) {
		return managers.get(type).getAll();
	}
}
