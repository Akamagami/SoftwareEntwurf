package backend.event.eventelement;

import backend.benutzer.Gruppe;
import backend.event.EventElement;
import constants.ClassType;

public class Catering extends EventElement {
	
	private String speisseangebot;
	private String menueBeschreibung;
	
	public Catering(String name, String beschreibung, double kosten, String id, String speisseangebot,
			String menueBeschreibung) {
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
		String p = d + "|" + d;
		
		String ret = ClassType.ELEMENTCATERING + d + super.toString() + d + speisseangebot + d + menueBeschreibung;
		ret+= this.getKontaktInfoList().toString() + this.getPictureList().toString();
		return ret;
	}	
}
