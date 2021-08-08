package backend.utils;

import java.util.ArrayList;

import backend.benutzer.Kontaktinformation;

public class KontaktInfoList {
	
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
}
