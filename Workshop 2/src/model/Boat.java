package model;
import java.io.IOException;

public class Boat extends ReadWriteFile{
	public Boat() throws IOException {
		super();
	}

	private String type;
	private String length;
	private String id;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public void setId(String i) {
		this.id = i;
	}

	public void registerBoat(Boat registerBoat) throws IOException {
		Integer countLines = 0;
		while(	readFromFile().readLine() != null) {
			countLines++;
			if(countLines.toString().equals(id)) {
				writeToFile().write(String.format("%16s", registerBoat.getType()));
				writeToFile().write(String.format("%17s", registerBoat.getLength()));
			}
		}
		writeToFile().close();
	}
}
