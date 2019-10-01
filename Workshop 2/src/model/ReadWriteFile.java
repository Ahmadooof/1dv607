package model;

import java.io.*;

public class ReadWriteFile implements IPersistence{
	BufferedWriter writer;
	BufferedReader reader;
	
	// true for append, false for overWrite.
	public ReadWriteFile() throws IOException {
		writer = new BufferedWriter(new FileWriter("members.txt",true));
		reader = new BufferedReader(new FileReader("members.txt"));
	}
	
	@Override
	public BufferedWriter writeToFile() throws IOException {
		return writer;
	}

	@Override
	public BufferedReader readFromFile() throws IOException {
		return reader;
	}

}
