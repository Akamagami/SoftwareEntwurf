package backend.importExport;
import constants.*;

public class ObjectData {
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