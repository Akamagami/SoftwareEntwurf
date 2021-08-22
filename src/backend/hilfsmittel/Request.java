package backend.hilfsmittel;

import java.util.Date;

import constants.ClassType;

public class Request {
	
	private int anzahl;
	private Date bis;
	private String id;
	private Hilfsmittel hilfsmittel;
	
	public Request(int anzahl, Date bis, Hilfsmittel hilfsmittel, String id) {
		super();
		this.anzahl = anzahl;
		this.bis = bis;
		this.id = id;
		this.hilfsmittel = hilfsmittel;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public Date getBis() {
		return bis;
	}

	public String getId() {
		return id;
	}

	public Hilfsmittel getHilfsmittel() {
		return hilfsmittel;
	}
	
	public void update(int anzahl) {
		this.anzahl = anzahl;
	}

	@Override
	public String toString() {
		String d = ",";
		String p = d + "|" + d;
		
		String ret = ClassType.REQUEST + d + id + d + bis + d + anzahl + p + ClassType.HILFSMITTEL + d + hilfsmittel.getId();
		
		return ret;
	}		
}
