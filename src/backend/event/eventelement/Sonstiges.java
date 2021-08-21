package backend.event.eventelement;

import backend.event.EventElement;
import constants.ClassType;

public class Sonstiges extends EventElement {
	private String Zustaendiger;
	
	public Sonstiges(String name, String beschreibung, double kosten, String Zustaendiger, String id) {
		super(name, beschreibung, kosten, id,ClassType.ELEMENTSONTIGES);
		this.Zustaendiger = Zustaendiger;
	}

	public void update(String name, String beschreibung, double kosten,String Zustaendiger) {
		// TODO Auto-generated method stub
		this.Zustaendiger = Zustaendiger;
		super.update(name, beschreibung, kosten);
	}
}
