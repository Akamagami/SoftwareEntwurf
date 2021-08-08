package backend.utils;

import java.util.ArrayList;

public class PictureList {
	
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
}
