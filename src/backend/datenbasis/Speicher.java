package backend.datenbasis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Gruppe;
import backend.benutzer.Kontaktinformation;
import backend.datenbasis.Manager.BenutzerManager;
import backend.datenbasis.Manager.ElementCateringManager;
import backend.datenbasis.Manager.ElementLocationManager;
import backend.datenbasis.Manager.ElementMusikManager;
import backend.datenbasis.Manager.ElementSonstigesManager;
import backend.datenbasis.Manager.EventManager;
import backend.datenbasis.Manager.GruppeManager;
import backend.datenbasis.Manager.HilfsmittelManager;
import backend.datenbasis.Manager.KontaktinformationManager;
import backend.datenbasis.Manager.RequestManager;
import backend.datenbasis.Manager.TeilEventManager;
import backend.datenbasis.Manager.ZuweisungManager;
import backend.datenbasis.factory.BGruppeFactory;
import backend.datenbasis.factory.BenutzerFactory;
import backend.datenbasis.factory.ElementCateringFactory;
import backend.datenbasis.factory.ElementLocationFactory;
import backend.datenbasis.factory.ElementMusikFactory;
import backend.datenbasis.factory.ElementSonstigesFactory;
import backend.datenbasis.factory.EventFactory;
import backend.datenbasis.factory.GruppeFactory;
import backend.datenbasis.factory.HilfsmittelFactory;
import backend.datenbasis.factory.KontaktInfoFactory;
import backend.datenbasis.factory.RequestFactory;
import backend.datenbasis.factory.TeilEventFactory;
import backend.datenbasis.factory.ZuweisungFactory;
import backend.event.Event;
import backend.event.EventElement;
import backend.event.TeilEvent;
import backend.event.eventelement.Catering;
import backend.event.eventelement.Location;
import backend.event.eventelement.Musik;
import backend.event.eventelement.Sonstiges;
import backend.hilfsmittel.Hilfsmittel;
import backend.hilfsmittel.Request;
import backend.hilfsmittel.Zuweisung;
import backend.importExport.CSVAdapter;
import backend.importExport.IReadWrite;
import backend.importExport.ObjectData;
import backend.utils.Picture;
import constants.ClassType;
import constants.Rollen;

public class Speicher {
	
	private HashMap<ClassType,EntityManager> managers = new HashMap<ClassType,EntityManager>();
	private HashMap<ClassType,ElementFactory> factories = new HashMap<ClassType,ElementFactory>();
	
	private IReadWrite csv = new CSVAdapter();
	private RollenVerwaltung rv = new RollenVerwaltung();

	public Speicher() {
		super();
		init();
		load();
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		//---------------------------------Manager-------------------------------------------//
		EntityManager<Benutzer> userManager = new BenutzerManager();
		managers.put(ClassType.BENUTZER,userManager);
		
		EntityManager<Kontaktinformation> kontaktinformationManager = new KontaktinformationManager();
		managers.put(ClassType.KONTAKTINFORMATION,kontaktinformationManager);
		
		EntityManager<Gruppe> gruppeManager = new GruppeManager();
		managers.put(ClassType.GRUPPE,gruppeManager);
		managers.put(ClassType.BGRUPPE,gruppeManager);
		
		EntityManager<Event> eventManager = new EventManager();
		managers.put(ClassType.EVENT,eventManager);
		
		EntityManager<TeilEvent> teilEventManager = new TeilEventManager();
		managers.put(ClassType.TEILEVENT,teilEventManager);
		
		EntityManager<Sonstiges> elementSonstigesManager = new ElementSonstigesManager();
		managers.put(ClassType.ELEMENTSONTIGES,elementSonstigesManager);
		
		EntityManager<Catering> elementCateringManager = new ElementCateringManager();
		managers.put(ClassType.ELEMENTCATERING,elementCateringManager);
		
		EntityManager<Musik> elementMusikManager = new ElementMusikManager();
		managers.put(ClassType.ELEMENTMUSIK,elementMusikManager);
		
		EntityManager<Location> elementLocationManager = new ElementLocationManager();
		managers.put(ClassType.ELEMENTLOCATION,elementLocationManager);
		
		EntityManager<Hilfsmittel> hilfsmittelManager = new HilfsmittelManager();
		managers.put(ClassType.HILFSMITTEL,hilfsmittelManager);
		
		EntityManager<Request> requestManager = new RequestManager();
		managers.put(ClassType.REQUEST,requestManager);
		
		EntityManager<Zuweisung> zuweisungManager = new ZuweisungManager();
		managers.put(ClassType.ZUWEISUNG,zuweisungManager);
		//---------------------------------Factories-------------------------------------------//
		ElementFactory userFactory = new BenutzerFactory();
		factories.put(ClassType.BENUTZER,userFactory);
		
		ElementFactory kontaktInfoFactory = new KontaktInfoFactory();
		factories.put(ClassType.KONTAKTINFORMATION,kontaktInfoFactory);
		
		ElementFactory gruppeFactory = new GruppeFactory();
		factories.put(ClassType.GRUPPE,gruppeFactory);
		
		ElementFactory bGruppeFactory = new BGruppeFactory();
		factories.put(ClassType.BGRUPPE,bGruppeFactory);
		
		ElementFactory eventFactory = new EventFactory();
		factories.put(ClassType.EVENT,eventFactory);
		
		ElementFactory teilEventFactory = new TeilEventFactory();
		factories.put(ClassType.TEILEVENT,teilEventFactory);
		
		ElementFactory elementSonstigesFactory = new ElementSonstigesFactory();
		factories.put(ClassType.ELEMENTSONTIGES,elementSonstigesFactory);
		
		ElementFactory elementCateringFactory = new ElementCateringFactory();
		factories.put(ClassType.ELEMENTCATERING,elementCateringFactory);
		
		ElementFactory elementMusikFactory = new ElementMusikFactory();
		factories.put(ClassType.ELEMENTMUSIK,elementMusikFactory);
		
		ElementFactory elementLocationFactory = new ElementLocationFactory();
		factories.put(ClassType.ELEMENTLOCATION,elementLocationFactory);
		
		ElementFactory hilfsmittelFactory = new HilfsmittelFactory();
		factories.put(ClassType.HILFSMITTEL,hilfsmittelFactory);
		
		ElementFactory zuweisungFactory = new ZuweisungFactory();
		factories.put(ClassType.ZUWEISUNG,zuweisungFactory);
		
		ElementFactory requestFactory = new RequestFactory();
		factories.put(ClassType.REQUEST,requestFactory);
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
		Object ret = factories.get(c).create(params, optId);
		this.save(c, ret);
		return ret;	
	}
/*--------------------------------------------------------------------------------------------------------------*/
	public void save() {
		ArrayList<String> ret = new ArrayList<String>();
		for(ClassType t:ClassType.values()) {
			for(Object o:this.getAll(t)) {
				ret.add(o.toString());
			}
		}
		csv.saveAll(ret);
	}
	public void load() {
		for(ObjectData o:csv.initRead()) {
			String[] data = o.splitData();
			switch(o.getType()) {
			case BENUTZER: this.createFromDataBenutzer(data); 
				break;
			case BGRUPPE:
				break;
			case ELEMENTCATERING:
				break;
			case ELEMENTLOCATION:
				break;
			case ELEMENTMUSIK:
				break;
			case ELEMENTSONTIGES:
				break;
			case EVENT:
				break;
			case GRUPPE:
				break;
			case HILFSMITTEL:
				break;
			case KONTAKTINFORMATION:
				break;
			case REQUEST:
				break;
			case TEILEVENT:
				break;
			case ZUWEISUNG:
				break;
			default:
				break;

			}
		}
	}
/*--------------------------------------------------------------------------------------------------------------*/
	private void createFromDataBenutzer(String data[]) {
		//create
		String[] mainData = data[0].split(",");
		String[] params = {mainData [2],mainData[3],mainData[1]};
		Benutzer tmp = (Benutzer) this.createObject(ClassType.BENUTZER,params);
		//config 
		for(String s:data) {
			String[] confData = s.split(",");
			if(ClassType.valueOf(confData[0]) == ClassType.KONTAKTINFORMATION) {
				tmp.addKontakt((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0] == "Rolle") {
				tmp.setRolle(rv.getRolle(Rollen.valueOf(confData[1])));
			} else if (confData[0] == "Picture"){
				Picture p = new Picture(confData[1],confData[2]);
				tmp.addBild(p);
			}
		}	
	}
	private void createFromDataKontaktinformation
/*--------------------------------------------------------------------------------------------------------------*/	

	public Object createObject(ClassType c,Object[] params) {
		return this.createObject(c, params,Optional.empty());
	}
}
