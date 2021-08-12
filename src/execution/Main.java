package execution;

import java.util.List;

import backend.benutzer.Benutzer;
import backend.datenbasis.Speicher;
import constants.ClassType;

public class Main {

	public static void main(String[] args) {
		System.out.println("wooooohhhweeeeeeee");
		Speicher sp = new Speicher();
		
		Object[] params = {"Felix","Radermacher"};
		Benutzer felix = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		Benutzer felix2 = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		params[0] = "Relix";
		params[1] = "Fadermacher";
		Benutzer felix3 = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		
		System.out.println(sp.getObject(ClassType.BENUTZER, "1").toString());
		System.out.println(sp.getObject(ClassType.BENUTZER, "2").toString());
		System.out.println(sp.getObject(ClassType.BENUTZER, "3").toString());
		
		for(Benutzer b:(List<Benutzer>)(List<?>) sp.getAll(ClassType.BENUTZER)) {
			System.out.println(b.toString());
		}
	}

}
