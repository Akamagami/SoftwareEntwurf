package constants;

public enum ClassType {
	BENUTZER("Benutzer"),
	KONTAKTINFORMATION("Kontaktinfo"),
	GRUPPE("Gruppe"),
	EVENT("Event"),
	TEILEVENT("Teilevent"),
	ELEMENTSONTIGES("Sonstiges"),
	ELEMENTCATERING("Catering"),
	ELEMENTLOCATION("Location"),
	ELEMENTMUSIK("Musik"),
	HILFSMITTEL("Hilfsmittel"),
	ZUWEISUNG("Zuweisung"),
	REQUEST("Auftrag"),
	BGRUPPE("Beschaffungsgruppe");
	
	private String displayName;
	
	ClassType(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
