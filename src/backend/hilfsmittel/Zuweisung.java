package backend.hilfsmittel;

import backend.event.TeilEvent;
import constants.ClassType;

public class Zuweisung {
	
	private TeilEvent teilevent;
	private Hilfsmittel hilfsmittel;
	private int menge;
	private String id;
	
	
public Zuweisung(TeilEvent teilevent, Hilfsmittel hilfsmittel, int menge, String id) {
		super();
		this.teilevent = teilevent;
		this.hilfsmittel = hilfsmittel;
		this.menge = menge;
		this.id = id;
	}
/*---------------------------------------------------------------------------------------------*/
	public TeilEvent getTeilevent() {
		return teilevent;
	}
	public Hilfsmittel getHilfsmittel() {
		return hilfsmittel;
	}
	public int getMenge() {
		return menge;
	}
	public String getId() {
		return id;
	}	
	
	public void update(int menge) {
		this.menge = menge;
	}
	
	@Override
	public String toString() {
		String d = ",";
		String p = d + "|" + d;
		
		String ret = ClassType.ZUWEISUNG + d + id + d + menge +  p + ClassType.HILFSMITTEL + d + hilfsmittel.getId() + p + ClassType.TEILEVENT + d + teilevent.getId();
		
		return ret;
	}		
	
}
