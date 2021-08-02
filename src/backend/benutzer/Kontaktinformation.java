package backend.benutzer;

public class Kontaktinformation {
/*------------------------------------------------------------------------------//
 * Kontaktinfos können, müssen aber nicht an Benutzer gebunden sein
 /------------------------------------------------------------------------------*/
	private String name;
	private String email;
	private String tele;
/*------------------------------------------------------------------------------*/
	public Kontaktinformation(String name, String email, String tele) {
		super();
		update(name,email,tele);
	}
/*------------------------------------------------------------------------------*/
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getTele() {
		return tele;
	}	
/*------------------------------------------------------------------------------*/
	public void update(String name, String email, String tele) {
		this.name = name;
		this.email = email;
		this.tele = tele;
	}
}
