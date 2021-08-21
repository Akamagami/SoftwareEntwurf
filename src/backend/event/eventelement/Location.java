package backend.event.eventelement;

import backend.event.EventElement;
import constants.ClassType;

public class Location extends EventElement {
	
	private String adresse;
	private String groesse;
	
	public Location(String name, String beschreibung, double kosten, String id, String adresse,
			String groesse) {
		super(name, beschreibung, kosten, id,ClassType.ELEMENTLOCATION);
		this.adresse = adresse;
		this.groesse = groesse;
	}

	public void update(String name, String beschreibung, double kosten,String adresse,
			String groesse) {
		// TODO Auto-generated method stub
		this.adresse = adresse;
		this.groesse = groesse;
		super.update(name, beschreibung, kosten);
	}
}
