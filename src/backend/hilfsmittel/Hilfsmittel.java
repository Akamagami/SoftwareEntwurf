package backend.hilfsmittel;

import backend.utils.PictureList;
import constants.ClassType;

public class Hilfsmittel {
	
	private String name;
	private String beschreibung;
	private int gesamtAnzahl;
	private String id;
	
	PictureList pictureList = new PictureList();

	public Hilfsmittel(String name, String beschreibung, int gesamtAnzahl, String id) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.gesamtAnzahl = gesamtAnzahl;
		this.id = id;
	}
/*---------------------------------------------------------------------------------------------*/
	public String getName() {
		return name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public int getGesamtAnzahl() {
		return gesamtAnzahl;
	}

	public String getId() {
		return id;
	}

	public PictureList getPictureList() {
		return pictureList;
	}	
/*---------------------------------------------------------------------------------------------*/	
	public void update(String name, String beschreibung, int gesamtAnzahl){
		this.name = name;
		this.beschreibung = beschreibung;
		this.gesamtAnzahl = gesamtAnzahl;
	}
	
	@Override
	public String toString() {
		String d = ",";
		String p = d + "|" + d;
		
		String ret = ClassType.HILFSMITTEL + d + id + d + beschreibung + d + gesamtAnzahl;
		ret+= this.getPictureList().toString();
		return ret;
	}		
}
