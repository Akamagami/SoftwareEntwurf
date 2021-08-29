package backend.event;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import backend.benutzer.Kontaktinformation;
import backend.utils.KontaktInfoList;
import backend.utils.Picture;
import backend.utils.PictureList;
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

	
	PictureList pictureList = new PictureList();
	KontaktInfoList kontaktInfoList = new KontaktInfoList();
	ArrayList<TeilEvent> TeilEventList = new ArrayList<TeilEvent>();
/*------------------------------------------------------------------*/	

	public Event(String titel, String beschreibung, Date start, Date end, int besucher, String kunde, double budget,
			 EventKategorie kategorie, String id) {
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
	public void addTeilEvent(TeilEvent ev)
	{
		TeilEventList.add(ev);
	}
/*------------------------------------------------------------------*/		
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

	public ArrayList<TeilEvent> getTeilEventList() {
		return TeilEventList;
	}

	public String getTitel() {
		return titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public int getBesucher() {
		return besucher;
	}

	public String getKunde() {
		return kunde;
	}

	public double getBudget() {
		return budget;
	}

	public String getId() {
		return id;
	}

	public EventKategorie getKategorie() {
		return kategorie;
	}

	public Status getStatus() {
		return status;
	}
	
	public PictureList getPictureList() {
		return pictureList;
	}

	public KontaktInfoList getKontaktInfoList() {
		return kontaktInfoList;
	}

	public void setStatus(Status status) {
		this.status = status;
		
	}

	/*------------------------------------------------------------------*/	
	public void update(String titel, String beschreibung, Date start, Date end, int besucher, String kunde, double budget,
			 EventKategorie kategorie, Status status) {
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.start = start;
		this.end = end;
		this.besucher = besucher;
		this.kunde = kunde;
		this.budget = budget;
		this.kategorie = kategorie;
		this.status = status;
	}
	/*------------------------------------------------------------------*/	
	 @Override
	public String toString() {
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.EVENT.getDisplayName() + d + id + d + titel + d + beschreibung + d + start + d + end + d + besucher + d + kunde + d + budget + d + kategorie + d + status;
		if(!TeilEventList.isEmpty()) {
			
			for(TeilEvent e:TeilEventList) {
				ret+= p + ClassType.TEILEVENT.getDisplayName();
				ret+= d + e.getId();
			}		
		}
		ret+= kontaktInfoList.toString() + pictureList.toString();
		return ret;
	}



}
