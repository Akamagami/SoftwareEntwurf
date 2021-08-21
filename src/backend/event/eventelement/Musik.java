package backend.event.eventelement;

import backend.event.EventElement;
import constants.ClassType;

public class Musik extends EventElement {
	
	private String kuenstlername;
	private String genre;
	
	public Musik(String name, String beschreibung, double kosten, String id, String kuenstlername,
			String genre) {
		super(name, beschreibung, kosten, id,ClassType.ELEMENTMUSIK);
		this.kuenstlername = kuenstlername;
		this.genre = genre;
	}

	public void update(String name, String beschreibung, double kosten,String kuenstlername,
			String genre) {
		// TODO Auto-generated method stub
		this.kuenstlername = kuenstlername;
		this.genre = genre;
		super.update(name, beschreibung, kosten);
	}
}
