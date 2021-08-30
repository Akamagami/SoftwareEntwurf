package backend.utils;

public class Picture {
	/*
	 * Bild mit pfad und altText
	 */
	private String url,Name;

	public Picture(String url, String name) {
		super();
		this.url = url;
		Name = name;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return Name;
	}	
	
}
