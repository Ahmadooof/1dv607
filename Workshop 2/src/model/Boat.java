package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Controller.member;
import view.CreateMember;

public class Boat {
	private String type;
	private double length;
	private String id;
	BufferedWriter writer;
	BufferedReader reader;


	public Boat(String type, double length, String i) {
		super();
		this.type = type;
		this.length = length;
		this.setId(i);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}

	public void setBoat(String type,double length, String id) throws IOException {
		reader = new BufferedReader(new FileReader("C:\\Users\\serwa\\git\\1dv607\\Workshop 2\\members.txt"));
		writer = new BufferedWriter(new FileWriter("C:\\Users\\serwa\\git\\1dv607\\Workshop 2\\members.txt", true));
		String line;
		Integer countLines = 0;
		while(	(line = reader.readLine()) != null) {	
			countLines++;
			if(countLines.toString().equals(id)) {
				writer.write(String.format("%16s", type));
				writer.write(String.format("%17s", length));
			}

		}

		writer.close();


	}
	public String getId() {
		return id;
	}
	public void setId(String i) {
		this.id = i;
	}


}
