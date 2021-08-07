package constants;

public enum Rollen {
	B ("Beschaffungspersonal"),
	A ("Administrator"),
	M ("Montageleiter"),
	P ("Personalmitarbeiter"),
	O ("Organisator");
	
	private String displayName;
	
	Rollen(String displayName) {
		this.displayName = displayName;
	}
	
	
	public String getDisplayName() {
		return displayName;
	}
}