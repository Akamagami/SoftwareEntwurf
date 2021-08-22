package backend.importExport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import constants.ClassType;

public class CSVAdapter implements IReadWrite {

	@Override
	public ArrayList<ObjectData> initRead() {
		 ArrayList<ObjectData> ret = new ArrayList<ObjectData>();
		 String row = "";
		 
		 BufferedReader csvReader;
		try {
			csvReader = new BufferedReader(new FileReader("I:\\test.csv"));
			 while ((row = csvReader.readLine()) != null) {
			     String[] data = row.split(",");
			     ObjectData temp = new ObjectData(ClassType.valueOf(data[0]),row);
			     ret.add(temp);
			 }
			 csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void saveAll(ArrayList<String> data) {
		try (FileWriter writer = new FileWriter(new File("I:\\test.csv"))) {		 
			 for(String line:data) {
				 writer.append(line);
				 writer.append("\n");
			 }
			 writer.flush();
			 writer.close();
	         System.out.println("done!");

	     } catch (Exception e) {
	         System.out.println(e.getMessage());
	     }
	}
}
