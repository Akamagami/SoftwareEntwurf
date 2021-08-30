package backend.importExport;
import constants.*;

public class ObjectData {
	
	/*
	 * Die gelesenen Strings werden vor dem aufruf der load() methode in ObjectData umgewandelt, Diese enthält immer den Class Type den der String beschreibt
	 */
	private ClassType type;
	private String data;
	
	public ObjectData(ClassType type, String data) {
		super();
		this.type = type;
		this.data = data;
	}
	
	public ClassType getType() {
		return type;
	}
	
	public String getData() {
		return data;
	}
	public String[] splitData() {
		return data.split("%,");
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ObjectData [type=").append(type).append(", data=").append(data).append("]");
		return builder.toString();
	}



}