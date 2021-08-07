package backend.event;
import java.util.ArrayList;
import java.util.Date;

import backend.benutzer.Kontaktinformation;
import backend.utils.Picture;
import constants.*;

public class Event {
/* beschreibt ein Event*/
	
	private String titel;
	private String beschreibung;	
	private Date start;
	private Date end;
	private int besucher;
	private String kunde;
	private double budget;
	private String id;//gets assigned by element factory
	
	private EventKategorie kategorie;
	private Status status;
	
	ArrayList<Picture> bildList = new ArrayList<Picture>();
	ArrayList<Kontaktinformation> kontaktInfoList = new ArrayList<Kontaktinformation>();
	ArrayList<TeilEvent> TeilEventList = new ArrayList<TeilEvent>();
/*------------------------------------------------------------------*/	

	public Event(String titel, String beschreibung, Date start, Date end, int besucher, String kunde, double budget,
			String id, EventKategorie kategorie) {
		super();
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.start = start;
		this.end = end;
		this.besucher = besucher;
		this.kunde = kunde;
		this.budget = budget;
		this.id = id;
		this.kategorie = kategorie;
		status = Status.E;
	}
	
/*------------------------------------------------------------------*/	
	public double summiereKosten()
	{
		double ret = 0.0;
		return ret;
	}

/*------------------------------------------------------------------*/	
	public void addPictureUrl(Picture p)
	{
		bildList.add(p);
	}
	public void addKontaktInfo(Kontaktinformation info)
	{
		kontaktInfoList.add(info);
	}
	public void addTeilEvent(TeilEvent ev)
	{
		TeilEventList.add(ev);
	}
/*------------------------------------------------------------------*/		
	public boolean removePicture(Picture p)
	{
		if(bildList.contains(p))
		{
			bildList.remove(p);
			return true;
		} else {
			return false;
		}
	}
	public boolean removePicture(int index)
	{
		if(index < bildList.size())
		{
			bildList.remove(index);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeKontakt(Kontaktinformation info)
	{
		if(kontaktInfoList.contains(info))
		{
			kontaktInfoList.remove(info);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeKontakt(int index)
	{
		if(index < kontaktInfoList.size())
		{
			kontaktInfoList.remove(index);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeTeilEvent(TeilEvent ev)
	{
		if(TeilEventList.contains(ev))
		{
			TeilEventList.remove(ev);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeTeilEvent(int index)
	{
		if(index < TeilEventList.size())
		{
			TeilEventList.remove(index);
			return true;
		} else {
			return false;
		}
	}
/*------------------------------------------------------------------*/	
}
