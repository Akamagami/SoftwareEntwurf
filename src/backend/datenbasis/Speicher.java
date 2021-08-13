package backend.datenbasis;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Manager.BenutzerManager;
import backend.datenbasis.Manager.EventElementManager;
import backend.datenbasis.Manager.EventManager;
import backend.datenbasis.Manager.GruppeManager;
import backend.datenbasis.Manager.HilfsmittelManager;
import backend.datenbasis.Manager.KontaktinformationManager;
import backend.datenbasis.Manager.RequestManager;
import backend.datenbasis.Manager.TeilEventManager;
import backend.datenbasis.Manager.ZuweisungManager;
import backend.event.Event;
import backend.event.EventElement;
import backend.event.TeilEvent;
import backend.hilfsmittel.Hilfsmittel;
import backend.hilfsmittel.Request;
import backend.hilfsmittel.Zuweisung;
import constants.ClassType;

public class Speicher {
	ElementFactory creator = new ElementFactory();
	
	private HashMap<ClassType,EntityManager> managers = new HashMap<ClassType,EntityManager>();
	

	public Speicher() {
		super();
		init();
	}
	
	private void init() {
		
		EntityManager<Benutzer> userManager = new BenutzerManager();
		managers.put(ClassType.BENUTZER,userManager);
		
		EntityManager<Kontaktinformation> kontaktinformationManager = new KontaktinformationManager();
		managers.put(ClassType.KONTAKTINFORMATION,kontaktinformationManager);
		
		EntityManager<Gruppe> gruppeManager = new GruppeManager();
		managers.put(ClassType.GRUPPE,gruppeManager);
		
		EntityManager<Event> eventManager = new EventManager();
		managers.put(ClassType.EVENT,eventManager);
		
		EntityManager<TeilEvent> teilEventManager = new TeilEventManager();
		managers.put(ClassType.TEILEVENT,teilEventManager);
		
		EntityManager<EventElement> eventElementManager = new EventElementManager();
		managers.put(ClassType.EVENTELEMENT,eventElementManager);
		
		EntityManager<Hilfsmittel> hilfsmittelManager = new HilfsmittelManager();
		managers.put(ClassType.HILFSMITTEL,hilfsmittelManager);
		
		EntityManager<Request> requestManager = new RequestManager();
		managers.put(ClassType.REQUEST,requestManager);
		
		EntityManager<Zuweisung> zuweisungManager = new ZuweisungManager();
		managers.put(ClassType.ZUWEISUNG,zuweisungManager);
	}
/*--------------------------------------------------------------------------------------------------------------*/	
	public void save(ClassType type, Object o) {
		managers.get(type).save(o);
	}
	public void update(ClassType type, String id,Object[] params) {
		managers.get(type).update(id,params);
	}
	public Object getObject(ClassType type, String id) {
		Optional ret =  managers.get(type).get(id);
		return ret.get();
	}
	public List<Object> getAll(ClassType type) {
		return managers.get(type).getAll();
	}
	public void delete(ClassType type, String id) {
		managers.get(type).delete(id);
	}
/*--------------------------------------------------------------------------------------------------------------*/	
	private Object createObject(ClassType c, Object[] params, Optional<String> optId) {
		Object ret = null;
		switch(c) {
			case BENUTZER: ret = creator.createBenutzer(params,optId); break;
			case KONTAKTINFORMATION: ret = creator.createKontaktinformation(params, optId); break ;
			case GRUPPE: ret = creator.createGruppe(params, optId); break ;
			case EVENT: ret = creator.createEvent(params, optId); break ;
			case TEILEVENT: ret = creator.createTeilEvent(params, optId); break ;
			case EVENTELEMENT: ret = creator.createEventElement(params, optId); break ;
			case HILFSMITTEL: ret = creator.createHilfsmittel(params, optId); break ;
			case REQUEST: ret = creator.createRequest(params, optId); break ;
			case ZUWEISUNG: ret = creator.createZuweisung(params, optId); break ;
			case BGRUPPE: ret = creator.createBGruppe(params, optId);c = ClassType.GRUPPE; break ;
		}
		this.save(c, ret);
		return ret;	
	}
/*--------------------------------------------------------------------------------------------------------------*/
	public Object createObject(ClassType c,Object[] params) {
		return this.createObject(c, params,Optional.empty());
	}
}
