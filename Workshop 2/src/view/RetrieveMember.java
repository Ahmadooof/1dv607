package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Controller.member;

public class RetrieveMember {


	BufferedReader reader;
	public RetrieveMember() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader("C:\\Users\\serwa\\git\\1dv607\\Workshop 2\\members.txt"));
	}

	public member retrieveMemberByID(String userInput) throws IOException {
		String line;
		member memberFound = new member();
		while(	(line = reader.readLine()) != null) {
			if(line.length()==31)
				continue;	
			memberFound.setName((String) line.subSequence(0, 15));
			memberFound.setPersonalNumber((String) line.subSequence(16, 31));
			memberFound.setId((String)line.subSequence(32, 46));
			memberFound.setId(memberFound.getId().replaceAll("\\D+", ""));
			if(memberFound.getId().equals(userInput)) {
				return memberFound;
			}
		}
		return null;
	}

	public void printUserNotFound() {
		System.out.println("User not found.");
	}
	
	public void printUserInfo(member memberInfo) {
		System.out.println("user Info:\n"
				+"Name: "+memberInfo.getName()
				+"\nPerosnalNumber: "+memberInfo.getPersonalNumber()
				+"\nID: "+memberInfo.getId());
	}

	public String printRetrieveMember() {
		Scanner input = new Scanner(System.in);
		System.out.println("please enter member ID:");
		return input.nextLine();
	}
}
