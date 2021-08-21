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

}
