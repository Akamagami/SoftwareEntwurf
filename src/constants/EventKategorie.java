package constants;

public enum EventKategorie {

	H("Hochzeit"),
	G("Geburtstag"),
	KG("Kongress"),
	KZ("Konzert"),
	E("Empfang"),
	V("Vernissage"),
	S("Sportveranstaltung"),
	W("Wettbewerb");
	
	private String displayName;
	
	EventKategorie(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
