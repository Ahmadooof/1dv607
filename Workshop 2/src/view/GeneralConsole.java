package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GeneralConsole {
	BufferedWriter writer;
	BufferedReader reader;
	int input;
	
	public GeneralConsole() throws IOException {
		writer = new BufferedWriter(new FileWriter("members.txt", true));
		reader= new BufferedReader(new FileReader("C:\\Users\\serwa\\git\\1dv607\\Workshop 2\\members.txt"));
	}

	public void printWelcome() {
		System.out.println("Hi and weclome to the club");
		System.out.println("1.Membership.");
		System.out.println("2.register boat.");
		System.out.println("3.view compact list");
	}


}
