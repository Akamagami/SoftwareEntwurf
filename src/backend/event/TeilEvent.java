package backend.event;

import java.util.ArrayList;
import java.util.Date;

import backend.benutzer.Benutzer;
import backend.benutzer.Beschaffungsgruppe;
import backend.benutzer.Gruppe;
import constants.Status;

public class TeilEvent {
	
	private String id;//gets assigned by element factory
	private Date start,ende;
	private String name;
	private Status status;
	private EventElement element;

	ArrayList<Gruppe> GruppenList = new ArrayList<Gruppe>(); //kann normale oder Beschaffungsgruppen enthalten
/*-----------------------------------------------------------------------------*/
	public TeilEvent(String id, Date start, Date ende, String name) {
		super();
		this.id = id;
		this.start = start;
		this.ende = ende;
		this.name = name;
		status = Status.E;
	}
/*-----------------------------------------------------------------------------*/	
	public void addGruppe(String name) {
		Gruppe add = new Gruppe(name);
		GruppenList.add(add);
	}
	public void addBeschaffungsGruppe(String name) {
		Gruppe add = new Beschaffungsgruppe(name);
		GruppenList.add(add);
	}
	public void deleteGruppe(int index) {
		GruppenList.remove(index);
	}
/*-----------------------------------------------------------------------------*/
	public boolean addMitarbeiterToGruppe(int index,Benutzer m) {
		return GruppenList.get(index).addMitarbeiter(m);
	}
	public boolean deleteMitarbeiterFromGruppe(int index,Benutzer m) {
		return GruppenList.get(index).deleteMitarbeiter(m);
	}
	public boolean deleteMitarbeiterFromGruppe(int index,int mIndex) {
		return GruppenList.get(index).deleteMitarbeiter(mIndex);
	}
	public boolean ernenneGruppenleiter(int index,Benutzer m) {
		if(GruppenList.get(index) instanceof Beschaffungsgruppe) {
			Beschaffungsgruppe tmp = (Beschaffungsgruppe) GruppenList.get(index);
			return  tmp.ernenneGruppenleiter(m);
		} else {
			return false;
		}
	}
	public boolean ernenneGruppenleiter(int index,int mIndex) {
		if(GruppenList.get(index) instanceof Beschaffungsgruppe) {
			Beschaffungsgruppe tmp = (Beschaffungsgruppe) GruppenList.get(index);
			return  tmp.ernenneGruppenleiter(mIndex);
		} else {
			return false;
		}
	}
/*-----------------------------------------------------------------------------*/
	public void addEventElement(EventElement ev) {
		element = ev;
	}
/*-----------------------------------------------------------------------------*/	
	public String getId() {
		return id;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnde() {
		return ende;
	}
	public String getName() {
		return name;
	}
	public Status getStatus() {
		return status;
	}
	public EventElement getElement() {
		return element;
	}
/*-----------------------------------------------------------------------------*/
	public void update(Date start, Date ende, String name, Status status) {
		this.start = start;
		this.ende = ende;
		this.name = name;
		this.status = status;
	}
}
