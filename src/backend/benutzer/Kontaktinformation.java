package backend.benutzer;

import constants.ClassType;

public class Kontaktinformation {
/*------------------------------------------------------------------------------//
 * Kontaktinfos k?nnen, m?ssen aber nicht an Benutzer gebunden sein
 * Kontaktinfos k?nnen auch an Events und Teilevents gekn?pft werden
 /------------------------------------------------------------------------------*/
	private String name;
	private String email;
	private String tele;
	private String id;
/*------------------------------------------------------------------------------*/
	public Kontaktinformation(String name, String email, String tele,String id) {
		super();
		this.id = id;
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
	public String getId() {
		return id;
	}	
/*------------------------------------------------------------------------------*/
	public void update(String name, String email, String tele) {
		this.name = name;
		this.email = email;
		this.tele = tele;
	}
/*------------------------------------------------------------------------------*/
	@Override
	public String toString() { //to string f?r csv adapter
		String d = ",";
		String p = d + "%" + d;
		
		String ret = ClassType.KONTAKTINFORMATION.getDisplayName() + d + id + d + name + d + email + d + tele;
		
		return ret;
	}
	
}
