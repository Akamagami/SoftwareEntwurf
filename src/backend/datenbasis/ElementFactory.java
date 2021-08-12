package backend.datenbasis;

import java.util.HashMap;
import java.util.Optional;

import backend.benutzer.Benutzer;
import constants.ClassType;

public class ElementFactory {
	
	private HashMap<ClassType,Integer> indexList = new HashMap<ClassType,Integer>();

	public ElementFactory() {
		super();
		init();
	}
	
	private void init() {
		for(ClassType c: ClassType.values()) {
			indexList.put(c,1);
		}
	}
	
	public void updateIndexList(String[] index) {
		int cnt = 0;
		for(ClassType c: ClassType.values()) {
			indexList.put(c,Integer.parseInt(index[cnt]));
			cnt++;
		}
	}
	public Integer[] getIndexList() {
		Integer[] ret = new Integer[indexList.size()];
		int cnt = 0;
		
		for(ClassType c: ClassType.values()) {
			ret[cnt] = indexList.get(c);
		}
		return ret;
	}
	
	public Benutzer createBenutzer(Object[] params,Optional<String> optId) {
		String newIndex = "0";
		if(optId.isPresent()) {
			newIndex = optId.get();
		} else {
			newIndex ="" + indexList.get(ClassType.BENUTZER);
			upId(ClassType.BENUTZER);
		}
		Benutzer ret = new Benutzer((String) params[0],(String) params[1],newIndex);
		return ret;
	}
	
	private void upId(ClassType c) {
		indexList.put(c, indexList.get(c)+1);
	}
	
}
