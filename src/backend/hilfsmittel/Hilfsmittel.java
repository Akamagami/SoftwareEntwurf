package backend.hilfsmittel;

import backend.utils.PictureList;
import constants.ClassType;

public class Hilfsmittel {
	/*
	 * EIn Hilfsmittel kann über eine Zuweisung einem Teilevent zugeordnet werden
	 */
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
	public String toString() {//toString fpr Speicher vorgang
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.HILFSMITTEL.getDisplayName() + d + id + d + name + d + beschreibung + d + gesamtAnzahl;
		ret+= this.getPictureList().toString();
		return ret;
	}		
}
