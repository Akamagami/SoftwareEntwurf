package backend.datenbasis;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import backend.benutzer.Benutzer;
import backend.benutzer.Beschaffungsgruppe;
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
import backend.utils.HilfsmittelUtils;
import backend.utils.Picture;
import constants.ClassType;
import constants.EventKategorie;
import constants.Rollen;
import constants.Status;

public class Speicher {
	/*
	 * Der Speicher ist die Schnittstelle für das UI um an alle Daten zu gelangen
	 * Der Speicher enthält alle Factories und entity Manager und alle util klassen
	 */

	private HashMap<ClassType, EntityManager> managers = new HashMap<ClassType, EntityManager>(); //Liste der ENtity Manager
	private HashMap<ClassType, ElementFactory> factories = new HashMap<ClassType, ElementFactory>();//Lister der Factories
	private HilfsmittelUtils hu = new HilfsmittelUtils(this); //hilfsmittel utils klasse
	private IReadWrite csv = new CSVAdapter();//Speicher adapter
	private RollenVerwaltung rv = new RollenVerwaltung();

	public Speicher() {
		super();
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		// ---------------------------------Manager-------------------------------------------// hier werden die Manager in die Hasmap eingefügt, der Key ist der entsprechende ClassType enum
		EntityManager<Benutzer> userManager = new BenutzerManager();
		managers.put(ClassType.BENUTZER, userManager);

		EntityManager<Kontaktinformation> kontaktinformationManager = new KontaktinformationManager();
		managers.put(ClassType.KONTAKTINFORMATION, kontaktinformationManager);

		EntityManager<Gruppe> gruppeManager = new GruppeManager(); //Da Bescaffungsgruppen auch gruppen sind werden sie von einem Manager verwaltet
		managers.put(ClassType.GRUPPE, gruppeManager);
		managers.put(ClassType.BGRUPPE, gruppeManager);

		EntityManager<Event> eventManager = new EventManager();
		managers.put(ClassType.EVENT, eventManager);

		EntityManager<TeilEvent> teilEventManager = new TeilEventManager();
		managers.put(ClassType.TEILEVENT, teilEventManager);

		EntityManager<Sonstiges> elementSonstigesManager = new ElementSonstigesManager();
		managers.put(ClassType.ELEMENTSONTIGES, elementSonstigesManager);

		EntityManager<Catering> elementCateringManager = new ElementCateringManager();
		managers.put(ClassType.ELEMENTCATERING, elementCateringManager);

		EntityManager<Musik> elementMusikManager = new ElementMusikManager();
		managers.put(ClassType.ELEMENTMUSIK, elementMusikManager);

		EntityManager<Location> elementLocationManager = new ElementLocationManager();
		managers.put(ClassType.ELEMENTLOCATION, elementLocationManager);

		EntityManager<Hilfsmittel> hilfsmittelManager = new HilfsmittelManager();
		managers.put(ClassType.HILFSMITTEL, hilfsmittelManager);

		EntityManager<Request> requestManager = new RequestManager();
		managers.put(ClassType.REQUEST, requestManager);

		EntityManager<Zuweisung> zuweisungManager = new ZuweisungManager();
		managers.put(ClassType.ZUWEISUNG, zuweisungManager);
		// ---------------------------------Factories-------------------------------------------//hier werden die Factories in die Hasmap eingefügt, der Key ist der entsprechende ClassType enum
		ElementFactory userFactory = new BenutzerFactory();
		factories.put(ClassType.BENUTZER, userFactory);

		ElementFactory kontaktInfoFactory = new KontaktInfoFactory();
		factories.put(ClassType.KONTAKTINFORMATION, kontaktInfoFactory);

		ElementFactory gruppeFactory = new GruppeFactory();//Gruppen haben gerade ids
		factories.put(ClassType.GRUPPE, gruppeFactory);

		ElementFactory bGruppeFactory = new BGruppeFactory();//BGruppen haben ungerade ids
		factories.put(ClassType.BGRUPPE, bGruppeFactory);

		ElementFactory eventFactory = new EventFactory();
		factories.put(ClassType.EVENT, eventFactory);

		ElementFactory teilEventFactory = new TeilEventFactory();
		factories.put(ClassType.TEILEVENT, teilEventFactory);

		ElementFactory elementSonstigesFactory = new ElementSonstigesFactory();
		factories.put(ClassType.ELEMENTSONTIGES, elementSonstigesFactory);

		ElementFactory elementCateringFactory = new ElementCateringFactory();
		factories.put(ClassType.ELEMENTCATERING, elementCateringFactory);

		ElementFactory elementMusikFactory = new ElementMusikFactory();
		factories.put(ClassType.ELEMENTMUSIK, elementMusikFactory);

		ElementFactory elementLocationFactory = new ElementLocationFactory();
		factories.put(ClassType.ELEMENTLOCATION, elementLocationFactory);

		ElementFactory hilfsmittelFactory = new HilfsmittelFactory();
		factories.put(ClassType.HILFSMITTEL, hilfsmittelFactory);

		ElementFactory zuweisungFactory = new ZuweisungFactory();
		factories.put(ClassType.ZUWEISUNG, zuweisungFactory);

		ElementFactory requestFactory = new RequestFactory();
		factories.put(ClassType.REQUEST, requestFactory);
	}

	/*--------------------------------------------------------------------------------------------------------------*/
	public void save(ClassType type, Object o) {//Funktion um ein Objekt einer Klasse im Entity Manager zu speichern, wird typischerweise immer direkt von der Create methode aufgerufen
		managers.get(type).save(o);
	}

	public void update(ClassType type, String id, Object[] params) {//Funktion um die update methode eiunes Objekts aufzurufen
		managers.get(type).update(id, params);
	}

	public Object getObject(ClassType type, String id) {//holt ein objekt je nach class type und id
		Optional ret = managers.get(type).get(id);
		return ret.get();
	}

	public List<Object> getAll(ClassType type) {//gibt alle object eines class types zurück
		return managers.get(type).getAll();
	}

	public void delete(ClassType type, String id) {//löscht ein Objekt aus dem jeiligen entity manager
		managers.get(type).delete(id);
	}

	/*--------------------------------------------------------------------------------------------------------------*/
	private Object createObject(ClassType c, Object[] params, Optional<String> optId) {//interne create methode, wird entweder mit oder ohne id aufgerufen
		Object ret = factories.get(c).create(params, optId);
		this.save(c, ret);
		return ret;
	}

	/*--------------------------------------------------------------------------------------------------------------*/
	public void save() {//erstellt Array List mit allen toString werten -> gibt diese an Csv Adapter weiter
		ArrayList<String> ret = new ArrayList<String>();
		for (ClassType t : ClassType.values()) {
			if (t != ClassType.BGRUPPE) {
				for (Object o : this.getAll(t)) {
					ret.add(o.toString());
				}
			}
		}
		csv.saveAll(ret);
	}

	public void load() {//erhält liste von csvAdapter, ruft basierend auf dem Class Type des zu erstellenden Objekted die richtige Methode auf
		for (ObjectData o : csv.initRead()) {
			String[] data = o.splitData();
			switch (o.getType()) {
			case BENUTZER:
				createFromDataBenutzer(data);
				break;
			case BGRUPPE:
				createFromDataBGruppe(data);
				break;
			case ELEMENTCATERING:
				createFromDataCatering(data);
				break;
			case ELEMENTLOCATION:
				createFromDataLocation(data);
				break;
			case ELEMENTMUSIK:
				createFromDataMusik(data);
				break;
			case ELEMENTSONTIGES:
				createFromDataSonstiges(data);
				break;
			case EVENT:
				createFromDataEvent(data);
				break;
			case GRUPPE:
				createFromDataGruppe(data);
				break;
			case HILFSMITTEL:
				createFromDataHilfsmittel(data);
				break;
			case KONTAKTINFORMATION:
				createFromDataKontaktinformation(data);
				break;
			case REQUEST:
				createFromDataRequest(data);
				break;
			case TEILEVENT:
				createFromDataTeilEvent(data);
				break;
			case ZUWEISUNG:
				createFromDataZuweisung(data);
				break;
			default:
				break;

			}
		}
		if(this.getAll(ClassType.HILFSMITTEL).size()<2) {
			this.createTestUserData();
		}
	}

	/*---------------------------------------------------------------
	 * Hier folgen die einzelnen Methoden die Aus den gespeicherten Strings Objekte erstellen
	 * WIchtig ist hier, dass für die enthaltenen Objekte eines Objekts nur die Ids gespeichert werden, daher müssen diese erstmal aus dem Speicher geholt werden
	 * ein speicher STring sieht so aus CLASSTYPE,id,main,data,strings,%,ClassType eins Objeks,id,%,ClassType eins Objeks,id,.......
	 * --------------------------------------------------------------*/
	private void createFromDataBenutzer(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3] };
		Benutzer tmp = (Benutzer) this.createObject(ClassType.BENUTZER, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.addKontakt((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Rolle")) {
				tmp.setRolle(rv.getRolle(Rollen.valueOf(confData[1])));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.addBild(p);
			}
		}
	}

	private void createFromDataKontaktinformation(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], mainData[4] };
		Kontaktinformation tmp = (Kontaktinformation) this.createObject(ClassType.KONTAKTINFORMATION, params,
				mainData[1]);
	}

	private void createFromDataGruppe(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2] };
		Gruppe tmp = (Gruppe) this.createObject(ClassType.GRUPPE, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.BENUTZER.getDisplayName())) {
				for (int i = 1; i < confData.length; i++) {
					tmp.addMitarbeiter((Benutzer) this.getObject(ClassType.BENUTZER, confData[i]));
				}
			}
		}
	}

	private void createFromDataBGruppe(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[3] };
		Beschaffungsgruppe tmp = (Beschaffungsgruppe) this.createObject(ClassType.BGRUPPE, params, mainData[2]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.BENUTZER.getDisplayName())) {
				for (int i = 1; i < confData.length; i++) {
					tmp.addMitarbeiter((Benutzer) this.getObject(ClassType.BENUTZER, confData[1]));
				}
			} else if (confData[0].equals("Leiter")) {
				tmp.ernenneGruppenleiter((Benutzer) this.getObject(ClassType.BENUTZER, confData[1]));
			}
		}
	}

	private void createFromDataCatering(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Double.parseDouble(mainData[4]), mainData[5], mainData[6] };
		Catering tmp = (Catering) this.createObject(ClassType.ELEMENTCATERING, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.getKontaktInfoList()
						.addKontaktInfo((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}

	private void createFromDataMusik(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Double.parseDouble(mainData[4]), mainData[5], mainData[6] };
		Musik tmp = (Musik) this.createObject(ClassType.ELEMENTMUSIK, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.getKontaktInfoList()
						.addKontaktInfo((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}

	private void createFromDataLocation(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Double.parseDouble(mainData[4]), mainData[5], mainData[6] };
		Location tmp = (Location) this.createObject(ClassType.ELEMENTLOCATION, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.getKontaktInfoList()
						.addKontaktInfo((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}

	private void createFromDataSonstiges(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Double.parseDouble(mainData[4]), mainData[5] };
		Sonstiges tmp = (Sonstiges) this.createObject(ClassType.ELEMENTSONTIGES, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.getKontaktInfoList()
						.addKontaktInfo((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}

	private void createFromDataHilfsmittel(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Integer.parseInt(mainData[4]) };
		Hilfsmittel tmp = (Hilfsmittel) this.createObject(ClassType.HILFSMITTEL, params, mainData[1]);
		// config
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}

	private void createFromDataZuweisung(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { this.getObject(ClassType.TEILEVENT, mainData[3]),
				this.getObject(ClassType.HILFSMITTEL, mainData[2]), Integer.parseInt(mainData[4]) };
		Zuweisung tmp = (Zuweisung) this.createObject(ClassType.ZUWEISUNG, params, mainData[1]);
	}

	private void createFromDataRequest(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { Integer.parseInt(mainData[2]), Date.valueOf(mainData[3]),
				this.getObject(ClassType.HILFSMITTEL, mainData[4]) };
		Request tmp = (Request) this.createObject(ClassType.REQUEST, params, mainData[1]);
	}

	private void createFromDataTeilEvent(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { Date.valueOf(mainData[3]), Date.valueOf(mainData[4]), mainData[2] };
		TeilEvent tmp = (TeilEvent) this.createObject(ClassType.TEILEVENT, params, mainData[1]);
		// config
		tmp.setStatus(Status.valueOf(mainData[5]));
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals("Element")) {
				tmp.addEventElement((EventElement) this.getObject(ClassType.valueOf(confData[1]), confData[2]));
			} else if (confData[0].equals(ClassType.GRUPPE.getDisplayName())) {
				tmp.addGruppe((Gruppe) this.getObject(ClassType.GRUPPE, confData[1]));
			}
		}
	}

	private void createFromDataEvent(String data[]) {
		// create
		String[] mainData = data[0].split(",");
		Object[] params = { mainData[2], mainData[3], Date.valueOf(mainData[4]), Date.valueOf(mainData[5]),Integer.parseInt(mainData[6]), mainData[7],
				Double.parseDouble(mainData[8]), EventKategorie.valueOf(mainData[9]) };
		Event tmp = (Event) this.createObject(ClassType.EVENT, params, mainData[1]);
		// config
		tmp.setStatus(Status.valueOf(mainData[10]));
		for (String s : data) {
			String[] confData = s.split(",");
			if (confData[0].equals(ClassType.TEILEVENT.getDisplayName())) {
				tmp.addTeilEvent(((TeilEvent) this.getObject(ClassType.TEILEVENT, confData[1])));
			} else if (confData[0].equals(ClassType.KONTAKTINFORMATION.getDisplayName())) {
				tmp.getKontaktInfoList()
						.addKontaktInfo((Kontaktinformation) this.getObject(ClassType.KONTAKTINFORMATION, confData[1]));
			} else if (confData[0].equals("Picture")) {
				Picture p = new Picture(confData[1], confData[2]);
				tmp.getPictureList().addPicture(p);
			}
		}
	}
	/*---------------------------------test data---------------------------------------------------------------*/
	public void createTestUserData() {//Diese methode erstellt eine Reihe an Benutzern und Hilfsmittel wenn ser SPeicher bisher leer war
        Object[] params = {"Felix","Radermacher"};  
        Benutzer felix = (Benutzer) this.createObject(ClassType.BENUTZER,params);
        Object [] ki1 = {"Felix", "FelixRad@gmail.com","+4689098"};
        felix.addKontakt((Kontaktinformation) this.createObject(ClassType.KONTAKTINFORMATION, ki1));
        felix.setRolle(rv.getRolle(Rollen.A));
        
        Object[] params1 = {"Sebastion","Adamois"};
        Benutzer sebastion = (Benutzer) this.createObject(ClassType.BENUTZER,params1);
        sebastion.setRolle(rv.getRolle(Rollen.B));
        Object [] ki2 = {"Sebbie", "AdamMotors@gmail.com","+4685434598"};
        sebastion.addKontakt((Kontaktinformation) this.createObject(ClassType.KONTAKTINFORMATION, ki2));

        
        Object[] params2 = {"Aron","Nroa"};
        Benutzer aron = (Benutzer) this.createObject(ClassType.BENUTZER,params2);
        aron.setRolle(rv.getRolle(Rollen.B));
        Object [] ki3 = {"Aron", "GNRon@gmail.com","+324234"};
        aron.addKontakt((Kontaktinformation) this.createObject(ClassType.KONTAKTINFORMATION, ki3));
        
        Object[] params3 = {"Potrs","Nammendorf"};
        Benutzer piot = (Benutzer) this.createObject(ClassType.BENUTZER,params3);
        piot.setRolle(rv.getRolle(Rollen.B));
        
        Object[] parrr = {"Baum", "Ein groï¿½er Baum", 42 };
        this.createObject(ClassType.HILFSMITTEL, parrr);
        Object[] parrr1 = {"Parkbank", "EIne Pank", 69 };
        this.createObject(ClassType.HILFSMITTEL, parrr1);
        Object[] parrr2 = {"Kunstrasen", "Vrum Vrum", 666 };
        this.createObject(ClassType.HILFSMITTEL, parrr2);
	}
	/*--------------------------------------------------------------------------------------------------------------*/

	public Object createObject(ClassType c, Object[] params) {//Diese Methode erstellt ein Objekt ohne eine Id festulegen, die ID wird automatisch generiert
		return this.createObject(c, params, Optional.empty());
	}

	private Object createObject(ClassType c, Object[] params, String id) {//Diese Methode erstellt ein Ojekt mit einer festgelegten id, kann nur vom speicher selbst aufgerufen werden
		return this.createObject(c, params, Optional.of(id));
	}

	
	public HilfsmittelUtils getHiflsmittelUtils() {//gibt die Hilfsmittelutils klasse zurück
		return hu;
	}
}
