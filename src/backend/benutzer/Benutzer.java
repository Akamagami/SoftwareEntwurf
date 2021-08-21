package backend.benutzer;

import backend.utils.Picture;
import constants.ClassType;

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
		this.id = id;
}
/*------------------------------------------------------------------------------*/
	public String[] fullName() {
		String[] ret =  {vorname,nachname};
		return ret;
	};
/*------------------------------------------------------------------------------*/
	public void addKontakt(Kontaktinformation ki) {
		kontaktInfo = ki;
	}
	public void addBild(Picture p) {
		picture = p;
	}
	public void setRolle(Rolle r) {
		rolle = r;
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
		String d = ",";
		String p = "|";
		
		String ret = ClassType.BENUTZER.getDisplayName() + d + id + d + vorname + d + nachname + p;
		ret += ClassType.KONTAKTINFORMATION + d + kontaktInfo.getId() + p;
		ret += rolle.getRollenName().getDisplayName() + p;
		ret += picture.getName() + d + picture.getUrl() + p;		
		return ret;

}
