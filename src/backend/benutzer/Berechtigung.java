package backend.benutzer;

public class Berechtigung {
/*Berechtigung, werden Rollen hinzugef�gt*/	
		
	private String id; // ID frei gew�hlt
	private String Bechreibung; // Hilfestellung
	
	public Berechtigung(String id, String bechreibung) {
		super();
		this.id = id;
		Bechreibung = bechreibung;
	}
	public String getId() {
		return id;
	}
	public String getBechreibung() {
		return Bechreibung;
	}
}
