package backend.event;

import java.util.ArrayList;

import backend.utils.KontaktInfoList;
import backend.utils.Picture;
import backend.utils.PictureList;

public abstract class EventElement {
	/*
	 * Event Element wird TeilEvent zugeordnet, 
	 * Unterklassen beschreiben eine bestimmte kategorie
	 */

	private String name;
	private String beschreibung;
	private double kosten;
	private String id;//assigned by element factory
	
	public EventElement(String name, String beschreibung, double kosten, String id) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.kosten = kosten;
		this.id = id;
	}
	
	PictureList pictureList = new PictureList();
	KontaktInfoList kontaktInfoList = new KontaktInfoList();
/*------------------------------------------------------------------------------------------*/

	public String getName() {
		return name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public double getKosten() {
		return kosten;
	}
	public String getId() {
		return id;
	}
	public PictureList getPictureList() {
		return pictureList;
	}
	public KontaktInfoList getKontaktInfoList() {
		return kontaktInfoList;
	}

/*------------------------------------------------------------------------------------------*/	
	public void update(String name, String beschreibung, double kosten) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.kosten = kosten;
	}
}
