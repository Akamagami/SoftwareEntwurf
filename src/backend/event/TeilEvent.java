package backend.event;

import java.util.ArrayList;
import java.sql.Date;

import backend.benutzer.Benutzer;
import backend.benutzer.Beschaffungsgruppe;
import backend.benutzer.Gruppe;
import constants.ClassType;
import constants.Status;

public class TeilEvent {
	
	private String id;//gets assigned by element factory
	private Date start,ende;
	private String name;
	private Status status;
	private EventElement element;

	ArrayList<Gruppe> GruppenList = new ArrayList<Gruppe>(); //kann normale oder Beschaffungsgruppen enthalten
/*-----------------------------------------------------------------------------*/
	public TeilEvent( Date start, Date ende, String name, String id) {
		super();
		this.id = id;
		this.start = start;
		this.ende = ende;
		this.name = name;
		status = Status.E;
	}
/*-----------------------------------------------------------------------------*/	
	public void addGruppe(Gruppe g) {
		
		GruppenList.add(g);
	}

	public void deleteGruppe(int index) {
		GruppenList.remove(index);
	}
	public Benutzer[] getBenutzer(){
		return GruppenList.get(0).getMitarbeiterArray();
	}
	public boolean hatGruppe() {
		if(GruppenList.size() <=0) {
			return false;
		} else {
			return true;
		}
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
	public boolean addBenutzer(Benutzer m) {
		return addMitarbeiterToGruppe(0, m);	
	}
	public boolean removeBenutzer(Benutzer m) {
		return deleteMitarbeiterFromGruppe(0, m);	
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
	public void setStatus(Status s) {
		status = s;
	}
/*-----------------------------------------------------------------------------*/
	public void update(Date start, Date ende, String name) {
		this.start = start;
		this.ende = ende;
		this.name = name;
	}
/*-----------------------------------------------------------------------------*/
	@Override
	public String toString() {
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.TEILEVENT.getDisplayName() + d + id + d + name + d + start + d + ende + d + status;
		ret += p+ "Element" + d + element.getType() + d + element.getId();
		if(!GruppenList.isEmpty()) {
			
			for(Gruppe g:GruppenList) {
				ret += p + ClassType.GRUPPE.getDisplayName(); 
				ret+= d + g.getId();
			}
		}
		return ret;
	}
	
}
