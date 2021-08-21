package backend.importExport;
import java.util.ArrayList;


public interface IReadWrite {
	
public ArrayList<ObjectData> initRead();

public void saveAll(ArrayList<ObjectData> data);

}
