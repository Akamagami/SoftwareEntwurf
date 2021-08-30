package backend.benutzer;

import java.util.ArrayList;

import constants.ClassType;
import constants.Rollen;

public class Gruppe {
//*
/*	normale Gruppe von Mitarbeitern die TeilEvent hinzugefügt werden kann
 * normalle gruppen sind im prinzip nur eine liste mit mitarbeitern
		*/
	private String name;
	private String id;
	private ArrayList<Benutzer> Mitarbeiter = new ArrayList<Benutzer>();//TODO
/*------------------------------------------------------------*/	
	public boolean addMitarbeiter(Benutzer m) //hinzufügen von Mitarbeiter, wird nicht zweimal hinzugefügt
	{
		if(!Mitarbeiter.contains(m))
		{
			Mitarbeiter.add(m);
			return true;
		} else {
			return false;
		}
	}
	public boolean deleteMitarbeiter(Benutzer m)//löschen eines mitarbeiter über objekt
	{
		if(Mitarbeiter.contains(m))
		{
			Mitarbeiter.remove(m);
			return true;
		} else {
			return false;
		}
	}
	public boolean deleteMitarbeiter(int index)//löschen eines mitarbeiter über index
	{
		if(index < Mitarbeiter.size())
		{
			Mitarbeiter.remove(index);
			return true;
		} else {
			return false;
		}
	}
/*------------------------------------------------------------*/
	public Gruppe(String name,String id) {
		super();
		this.name = name;
		this.id = id;
	}	
/*------------------------------------------------------------*/
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
/*------------------------------------------------------------*/
	public Benutzer[] getMitarbeiterArray() { //liefert array mit allen benutzers für UI
		Benutzer[] ret = new Benutzer[Mitarbeiter.size()];
		int cnt = 0;
		for(Benutzer b : Mitarbeiter)
		{
			ret[cnt] = b;
			cnt++;
		}
		return ret;
	}
/*------------------------------------------------------------*/
	public Benutzer getMitarbeiter(int index) { //gibt einzelnen mitarbeiter zturück
		return Mitarbeiter.get(index);
	}
	public int getAnzahl()
	{
		return Mitarbeiter.size();
	}
/*------------------------------------------------------------*/
	public boolean hatMitarbeiter(Benutzer m) {//liefert boolean ob mitarbeiter vorhanden ist zurück
		if(Mitarbeiter.contains(m))
		{
			return true;
		} else {
			return false;
		}
	}
/*------------------------------------------------------------*/
	@Override
	public String toString() { //to string für csv adapter
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.GRUPPE.getDisplayName() + d + id + d + name;
		if(!Mitarbeiter.isEmpty()) {
			
			for(Benutzer b: Mitarbeiter) {
				ret += p + ClassType.BENUTZER.getDisplayName();
				ret += d + b.getId();
			}
		}	
		return ret;
	}
}
