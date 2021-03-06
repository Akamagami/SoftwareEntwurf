package backend.event.eventelement;

import backend.event.EventElement;
import constants.ClassType;

public class Musik extends EventElement {
	/*
	 * Eventelement Musik
	 * ebth?lt 2 zus?tzliche felder
	 */
	private String kuenstlername;
	private String genre;
	
	public Musik(String name, String beschreibung, double kosten, String kuenstlername,
			String genre, String id) {
		super(name, beschreibung, kosten, id,ClassType.ELEMENTMUSIK);
		this.kuenstlername = kuenstlername;
		this.genre = genre;
	}

	public void update(String name, String beschreibung, double kosten,String kuenstlername,
			String genre) {
		// TODO Auto-generated method stub
		this.kuenstlername = kuenstlername;
		this.genre = genre;
		super.update(name, beschreibung, kosten);
	}
	@Override
	public String toString() {//toString f?r Speicherfunktion
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.ELEMENTMUSIK.getDisplayName() + d + super.toString() + d + kuenstlername + d + genre;
		ret+= this.getKontaktInfoList().toString() + this.getPictureList().toString();
		return ret;
	}

	public String getKuenstlername() {
		return kuenstlername;
	}

	public void setKuenstlername(String kuenstlername) {
		this.kuenstlername = kuenstlername;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
