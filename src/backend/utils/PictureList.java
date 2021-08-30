package backend.utils;

import java.util.ArrayList;

import backend.benutzer.Kontaktinformation;
import constants.ClassType;

public class PictureList {
	/*
	 * Liste mit Kontaktinfoamtionen
	 */
	ArrayList<Picture> bildList = new ArrayList<Picture>();
	
/*----------------------------------------------------------*/
	public boolean removePicture(Picture p)
	{
		if(bildList.contains(p))
		{
			bildList.remove(p);
			return true;
		} else {
			return false;
		}
	}
	public boolean removePicture(int index)
	{
		if(index < bildList.size())
		{
			bildList.remove(index);
			return true;
		} else {
			return false;
		}
	}
/*----------------------------------------------------------*/
	public void addPicture(Picture p)
	{
		bildList.add(p);
	}
/*----------------------------------------------------------*/	
	public Picture[] getPictures() {
		Picture[] ret = new Picture[bildList.size()];
		int cnt = 0;
		for(Picture p : bildList)
		{
			ret[cnt] = p;
			cnt++;
		}
		return ret;
	}
/*----------------------------------------------------------*/	
	@Override
	public String toString()
	{//toString für Speicherfunktion
		String d = ",";
		String p = d + "%" + d;
		
		String ret = "";
		if(!bildList.isEmpty()) {
			
			
			for(Picture b: bildList) {
				ret+= p;
				ret += "Picture" + d + b.getUrl() + d + b.getName();
			}
		}
		return ret;
	}
}
