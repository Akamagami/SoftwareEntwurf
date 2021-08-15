package constants;

public enum Rollen {
	B ("Beschaffungspersonal"),
	A ("Administrator"),
	M ("Montageleiter"),
	P ("Personalmitarbeiter"),
	O ("Organisator"),
	N ("none");
	
	private String displayName;
	
	Rollen(String displayName) {
		this.displayName = displayName;
	}
	
	
	public String getDisplayName() {
		return displayName;
	}
	
}