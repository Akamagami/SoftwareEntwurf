package backend.benutzer;

import java.util.ArrayList;

import constants.Rollen;

public class Gruppe {
//normale Gruppe von MItarbeitern die Event hinzugefügt werden kann	
	private String name;
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
/*------------------------------------------------------------*/
	public Gruppe(String name) {
		super();
		this.name = name;
	}	
/*------------------------------------------------------------*/
	public String getName() {
		return name;
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
	public boolean hatMitarbeiter(Benutzer m) {
		if(!Mitarbeiter.contains(m))
		{
			return true;
		} else {
			return false;
		}
	}
}
