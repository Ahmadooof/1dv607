package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.Id;
import view.console;

public class member {
	private String name;
	private int personalNumber;
	private int id;
	BufferedReader reader;
	Scanner input = new Scanner(System.in);

	public member() throws FileNotFoundException {
		super();
		reader= new BufferedReader(new FileReader("C:\\Users\\ahmad\\Desktop\\eclipse-workspace\\Workshop 2\\members.txt"));
	}

	//    ****************** Getters And Setters *************

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public void wantsToRegister(console view) {

	}

	public void checkUserInput(console view, Id memberId) throws IOException {
		if(input.nextInt() == 1) {
			System.out.println("yes");
			view.registerMember(memberId, this);
		}
	}

}
