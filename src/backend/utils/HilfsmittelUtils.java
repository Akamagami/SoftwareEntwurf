package backend.utils;

import java.sql.Date;
import java.util.ArrayList;

import backend.datenbasis.Speicher;
import backend.hilfsmittel.Hilfsmittel;
import backend.hilfsmittel.Zuweisung;
import constants.ClassType;

public class HilfsmittelUtils {
	private Speicher speicher;
	
	public HilfsmittelUtils(Speicher speicher) {
		super();
		this.speicher = speicher;
	}
	
	public int getBestand(String id, Date start, Date ende) {
		Hilfsmittel h = (Hilfsmittel) speicher.getObject(ClassType.HILFSMITTEL, id);
		int anzahl = h.getGesamtAnzahl();
		for(Zuweisung zu: (ArrayList<Zuweisung>)(ArrayList<?>) speicher.getAll(ClassType.ZUWEISUNG)) {
			if(zu.getHilfsmittel().getId().equals(h.getId())) {
				if(((zu.getTeilevent().getStart().compareTo(start)>=0) && (zu.getTeilevent().getStart().compareTo(ende)<=0)) || 
				   ((zu.getTeilevent().getEnde().compareTo(start)>=0) && (zu.getTeilevent().getEnde().compareTo(ende)<=0))) {
					anzahl += -zu.getMenge();//zeitraüme überlappen sich
				} else if((zu.getTeilevent().getStart().compareTo(start)<=0) && (zu.getTeilevent().getEnde().compareTo(ende)>=0)) {
					anzahl += -zu.getMenge();//zeitraum liegt ionnerhalb des anderen
				}
			}
		}
		return anzahl;
	}

}
