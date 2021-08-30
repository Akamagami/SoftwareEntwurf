package backend.event.eventelement;

import backend.benutzer.Gruppe;
import backend.event.EventElement;
import constants.ClassType;

public class Catering extends EventElement {
	/*
	 * Eventelement Catering
	 * enthält 2 ztusätzliche Felder
	 */
	private String speisseangebot;
	private String menueBeschreibung;
	
	public Catering(String name, String beschreibung, double kosten, String speisseangebot,
			String menueBeschreibung, String id) {
		super(name, beschreibung, kosten, id,ClassType.ELEMENTCATERING);
		this.speisseangebot = speisseangebot;
		this.menueBeschreibung = menueBeschreibung;
		
	}

	public void update(String name, String beschreibung, double kosten,String speisseangebot,
			String menueBeschreibung) {
		this.speisseangebot = speisseangebot;
		this.menueBeschreibung = menueBeschreibung;
		super.update(name, beschreibung, kosten);
	}
	@Override
	public String toString() {
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.ELEMENTCATERING.getDisplayName() + d + super.toString() + d + speisseangebot + d + menueBeschreibung;
		ret+= this.getKontaktInfoList().toString() + this.getPictureList().toString();
		return ret;
	}

	public String getSpeisseangebot() {
		return speisseangebot;
	}

	public void setSpeisseangebot(String speisseangebot) {
		this.speisseangebot = speisseangebot;
	}

	public String getMenueBeschreibung() {
		return menueBeschreibung;
	}

	public void setMenueBeschreibung(String menueBeschreibung) {
		this.menueBeschreibung = menueBeschreibung;
	}
}
