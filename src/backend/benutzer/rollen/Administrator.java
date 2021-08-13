package backend.benutzer.rollen;

import backend.benutzer.Rolle;
import constants.Rollen;

public class Administrator extends Rolle {
	public Administrator() {
		super();
		rollenName = Rollen.A;
	}
}
