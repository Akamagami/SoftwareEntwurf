package backend.benutzer;

import java.util.ArrayList;

import constants.ClassType;
import constants.Rollen;

public class Gruppe {
//normale Gruppe von MItarbeitern die Event hinzugefügt werden kann	
	private String name;
	private String id;
	private ArrayList<Benutzer> Mitarbeiter = new ArrayList<Benutzer>();//TODO
/*------------------------------------------------------------*/	
	public boolean addMitarbeiter(Benutzer m)
	{
		if(!Mitarbeiter.contains(m))
		{
			Mitarbeiter.add(m);
			return true;
		} else {
			return false;
		}
	}
	public boolean deleteMitarbeiter(Benutzer m)
	{
		if(Mitarbeiter.contains(m))
		{
			Mitarbeiter.remove(m);
			return true;
		} else {
			return false;
		}
	}
	public boolean deleteMitarbeiter(int index)
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
	public Benutzer[] getMitarbeiterArray() {
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
	public Benutzer getMitarbeiter(int index) {
		return Mitarbeiter.get(index);
	}
	public int getAnzahl()
	{
		return Mitarbeiter.size();
	}
/*------------------------------------------------------------*/
	public boolean hatMitarbeiter(Benutzer m) {
		if(Mitarbeiter.contains(m))
		{
			return true;
		} else {
			return false;
		}
	}
/*------------------------------------------------------------*/
	@Override
	public String toString() {
		String d = ",";
		String p = d + "|" + d;
		
		String ret = ClassType.GRUPPE + d + id + d + name + p;
		if(!Mitarbeiter.isEmpty()) {
			ret += ClassType.BENUTZER;
			for(Benutzer b: Mitarbeiter) {
				ret += d + b.getId();
			}
		}	
		return ret;
	}
}
