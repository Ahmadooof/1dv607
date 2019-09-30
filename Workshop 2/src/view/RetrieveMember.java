package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RetrieveMember {


	BufferedReader reader;
	public RetrieveMember() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader("C:\\Users\\ahmad\\Desktop\\eclipse-workspace\\Workshop 2\\members.txt"));
	}

	public boolean retrieveMemberByID(String userInput) throws IOException {
		String line, ID, name, personalNumber;
		while(	(line = reader.readLine()) != null) {
			if(line.length()==31)
				continue;	
			name = (String) line.subSequence(0, 15);
			personalNumber = (String) line.subSequence(16, 31);
			ID = (String) line.subSequence(32, 46);
			ID = ID.replaceAll("\\D+", "");
			if(ID.equals(userInput)) {
				System.out.println("user Info:\n"
						+"Name: "+name+"\nPerosnalNumber: "+personalNumber+"\nID: "+ID);
				return true;
			}
		}
		return false;
	}

	public void printUserNotFound() {
		System.out.println("User not found.");
	}
	
	public String printRetrieveMember() {
		Scanner input = new Scanner(System.in);
		System.out.println("please enter member ID:");
		return input.nextLine();
	}
}
