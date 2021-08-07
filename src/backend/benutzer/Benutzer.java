package backend.benutzer;

public class Benutzer {
/*------------------------------------------------------------------------------//
 * Die Klasse Benutzer beschreibt einen Mitarbeiter des Unternehmens
 /------------------------------------------------------------------------------*/
	private String vorname; 
	private String nachname;
	private String bildUrl;
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
/*------------------------------------------------------------------------------*/	
	public Rolle getRolle()
	{
		return rolle;
	}
}
