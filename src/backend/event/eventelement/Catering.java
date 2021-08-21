package backend.event.eventelement;

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
	
}
