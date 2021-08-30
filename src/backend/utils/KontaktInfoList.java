package backend.utils;

import java.util.ArrayList;

import backend.benutzer.Kontaktinformation;
import constants.ClassType;

public class KontaktInfoList {
	/*
	 * Liste mit Kontaktinfoamtionen
	 */
	ArrayList<Kontaktinformation> kontaktInfoList = new ArrayList<Kontaktinformation>();
	
/*----------------------------------------------------------*/
	public boolean removeKontakt(Kontaktinformation info)
	{
		if(kontaktInfoList.contains(info))
		{
			kontaktInfoList.remove(info);
			return true;
		} else {
			return false;
		}
	}
	public boolean removeKontakt(int index)
	{
		if(index < kontaktInfoList.size())
		{
			kontaktInfoList.remove(index);
			return true;
		} else {
			return false;
		}
	}
/*----------------------------------------------------------*/
	public void addKontaktInfo(Kontaktinformation info)
	{
		kontaktInfoList.add(info);
	}
/*----------------------------------------------------------*/	
	public Kontaktinformation[] getKontaktInfos() {
		Kontaktinformation[] ret = new Kontaktinformation[kontaktInfoList.size()];
		int cnt = 0;
		for(Kontaktinformation k : kontaktInfoList)
		{
			ret[cnt] = k;
			cnt++;
		}
		return ret;
	}
	/*----------------------------------------------------------*/	
	@Override
	public String toString() {//toString für Speicherfunktion
		String d = ",";
		String p = d + "%" + d;
		
		String ret = "";
		if(!kontaktInfoList.isEmpty()) {
			
			
			for(Kontaktinformation k: kontaktInfoList) {
				ret+= p + ClassType.KONTAKTINFORMATION.getDisplayName();
				ret += d + k.getId();
			}
		}
		return ret;
	}
}
