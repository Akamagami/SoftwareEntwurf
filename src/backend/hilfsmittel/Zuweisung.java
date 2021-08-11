package backend.hilfsmittel;

import backend.event.TeilEvent;

public class Zuweisung {
	
	private TeilEvent teilevent;
	private Hilfsmittel hilfsmittel;
	private int menge;
	private String id;
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
	
}
