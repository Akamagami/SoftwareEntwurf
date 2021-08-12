package backend.benutzer;

import backend.utils.Picture;

public class Benutzer {
/*------------------------------------------------------------------------------//
 * Die Klasse Benutzer beschreibt einen Mitarbeiter des Unternehmens
 /------------------------------------------------------------------------------*/
	private String vorname; 
	private String nachname;
	private Picture picture;
	private String id; // id word von ElementFactory bestimmt
	
	private Kontaktinformation kontaktInfo; // kontaktinfo enthält Name, Email und telkefon
	private Rolle rolle; // Rolle beschreibt die ROlle in der Firma, Rolle ist eine Abstracte Klasse
	
	
	public Benutzer(String vorname, String nachname, String id) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
}
/*------------------------------------------------------------------------------*/
	public String[] fullName() {
		String[] ret =  {vorname,nachname};
		return ret;
	};
/*------------------------------------------------------------------------------*/
	public void kontaktInfosHinzufuegen(Kontaktinformation ki) {
		kontaktInfo = ki;
	}
	public void addBild(Picture p) {
		picture = p;
	}
	
/*------------------------------------------------------------------------------*/	
	public Rolle getRolle()
	{
		return rolle;
	}
	public Picture getPicture() {
		return picture;
	}
	public String getId() {
		return id;
	}
	public Kontaktinformation getKontaktInfo() {
		return kontaktInfo;
	}
/*------------------------------------------------------------------------------*/
	public void update()
	{
		
	}
/*------------------------------------------------------------------------------*/
	@Override
	public String toString() {
		return "Benutzer [vorname=" + vorname + ", nachname=" + nachname + ", picture=" + picture + ", id=" + id
				+ ", kontaktInfo=" + kontaktInfo + ", rolle=" + rolle + "]";
	}

}
