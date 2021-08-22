package constants;

public enum ClassType {
	KONTAKTINFORMATION("Kontaktinfo"),
	BENUTZER("Benutzer"),	
	BGRUPPE("Beschaffungsgruppe"),
	GRUPPE("Gruppe"),
	ELEMENTSONTIGES("Sonstiges"),
	ELEMENTCATERING("Catering"),
	ELEMENTLOCATION("Location"),
	ELEMENTMUSIK("Musik"),
	TEILEVENT("Teilevent"),
	EVENT("Event"),
	HILFSMITTEL("Hilfsmittel"),
	ZUWEISUNG("Zuweisung"),
	REQUEST("Auftrag");
	
	
	private String displayName;
	
	ClassType(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
