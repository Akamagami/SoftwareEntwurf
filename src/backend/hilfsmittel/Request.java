package backend.hilfsmittel;

import java.util.Date;

public class Request {
	
	private int anzahl;
	private Date bis;
	private String id;
	private Hilfsmittel hilfsmittel;
	
	public Request(int anzahl, Date bis, String id, Hilfsmittel hilfsmittel) {
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
	
	
	
}
