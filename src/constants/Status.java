package constants;

public enum Status {
	E("Erstellt"),
	G("Geplant"),
	IA("in Arbeit"),
	F("Fertig"),
	S("Storniert"),
	P("Pausiert");
	
	private String displayName;
	
	Status(String displayName) {
		this.displayName = displayName;
	}
	
	
	public String getDisplayName() {
		return displayName;
	}
}