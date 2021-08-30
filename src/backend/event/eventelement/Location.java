package backend.event.eventelement;

import backend.event.EventElement;
import constants.ClassType;

public class Location extends EventElement {
	/*
	 * Eventelement Location
	 * ebthält 2 zusaätzliche felder
	 */
	private String adresse;
	private String groesse;
	
	public Location(String name, String beschreibung, double kosten, String adresse,
			String groesse, String id) {
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
	@Override
	public String toString() {
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.ELEMENTLOCATION.getDisplayName() + d + super.toString() + d + adresse + d + groesse;
		ret+= this.getKontaktInfoList().toString() + this.getPictureList().toString();
		return ret;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
}
