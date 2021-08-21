package execution;

import java.util.List;

import backend.benutzer.*;
import backend.benutzer.rollen.*;
import backend.datenbasis.*;
import constants.ClassType;

public class Main {

	
	public static void main(String[] args) {
		
		
		
		
		Speicher sp = new Speicher();
		
		Rolle besch = new Beschaffungspersonal();

		System.out.println("--------------------------------Kontaktinfoamtion-----------------------------///?/");
		Object[] params1 = {"Email","adresse idk","+7889846546 654 966 5847 6 8"};
		
		Kontaktinformation ktk1 = (Kontaktinformation) sp.createObject(ClassType.KONTAKTINFORMATION,params1);
		Kontaktinformation ktk2 = (Kontaktinformation) sp.createObject(ClassType.KONTAKTINFORMATION,params1);
		System.out.println(sp.getObject(ClassType.KONTAKTINFORMATION, "1").toString());
		System.out.println(sp.getObject(ClassType.KONTAKTINFORMATION, "2").toString());
		sp.delete(ClassType.KONTAKTINFORMATION,"1");
		params1[2] = "hat kein phone zu arm";
		sp.update(ClassType.KONTAKTINFORMATION, "2", params1);
		for(Kontaktinformation b:(List<Kontaktinformation>)(List<?>) sp.getAll(ClassType.KONTAKTINFORMATION)) {
			System.out.println(b.toString());
		}
		System.out.println("--------------------------------Benutzer--------------------------------------///?/");
		
		Object[] params = {"Felix","Radermacher"};
		Benutzer felix = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		Benutzer felix2 = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		params[0] = "Relix";
		params[1] = "Fadermacher";
		Benutzer felix3 = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		
		felix.setRolle(besch);
		
		System.out.println(sp.getObject(ClassType.BENUTZER, "1").toString());
		System.out.println(sp.getObject(ClassType.BENUTZER, "2").toString());
		System.out.println(sp.getObject(ClassType.BENUTZER, "3").toString());
		sp.delete(ClassType.BENUTZER,"2");
		
		Benutzer change = (Benutzer) sp.getObject(ClassType.BENUTZER, "3");
		change.addKontakt((Kontaktinformation) sp.getObject(ClassType.KONTAKTINFORMATION, "2"));
		
		Benutzer felix4 = (Benutzer) sp.createObject(ClassType.BENUTZER,params);
		for(Benutzer b:(List<Benutzer>)(List<?>) sp.getAll(ClassType.BENUTZER)) {
			System.out.println(b.toString());
		}
		System.out.println("--------------------------------Gruppe--------------------------------------///?/");
		Object[] paramsGruppe = {"DeinErnst yryr"};
		sp.createObject(ClassType.GRUPPE, paramsGruppe);
		sp.createObject(ClassType.GRUPPE, paramsGruppe);
		sp.createObject(ClassType.BGRUPPE, paramsGruppe);
		Beschaffungsgruppe g1 = (Beschaffungsgruppe) sp.getObject(ClassType.BGRUPPE, "1");
		for(Benutzer b:(List<Benutzer>)(List<?>) sp.getAll(ClassType.BENUTZER)) {
			g1.addMitarbeiter(b);
		}
		Gruppe g2 = (Gruppe) sp.getObject(ClassType.GRUPPE, "4");
		
		for(Benutzer b:(List<Benutzer>)(List<?>) sp.getAll(ClassType.BENUTZER)) {
			g2.addMitarbeiter(b);
		}
		g1.ernenneGruppenleiter((Benutzer) (sp.getObject(ClassType.BENUTZER, "1")));
		
		for(Gruppe b:(List<Gruppe>)(List<?>) sp.getAll(ClassType.GRUPPE)) {
			System.out.println(b.toString());
		}
	}
	
	
	
	

}
