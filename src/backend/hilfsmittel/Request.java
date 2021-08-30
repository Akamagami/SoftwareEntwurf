package backend.hilfsmittel;

import java.util.Date;

import constants.ClassType;

public class Request {
	/*
	 * Request dienen der Anforderung einer AUfstockung
	 * Es wird eine Anzahl für ein Hilfsmittel festgelegt
	 * Es wird auch ein bis datum gesetzt
	 */
	
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
	
	public void update(int anzahl) {//erhöhung oder verringern der Anzahl
		this.anzahl = anzahl;
	}

	@Override
	public String toString() {//toString für Speicherfunktion
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.REQUEST.getDisplayName() + d + id + d + anzahl + d + bis +  d + hilfsmittel.getId();
		
		return ret;
	}		
}
