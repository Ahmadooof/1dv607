package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Controller.member;
import model.Id;

public class CreateMember {

	BufferedWriter writer;
	BufferedReader reader;
	Scanner sc = new Scanner(System.in);

	public CreateMember() throws IOException {
		writer = new BufferedWriter(new FileWriter("members.txt", true));
		reader= new BufferedReader(new FileReader("C:\\Users\\serwa\\git\\1dv607\\Workshop 2\\members.txt"));
	}
	
	public void registerMember(Id memberId, member member) throws IOException {
		checkFile();
		writer.write("\n");
		System.out.println("Enter your name:");
		member.setName(sc.nextLine());
		writer.write(String.format("%-16s", member.getName()));
		System.out.println("Enter your personal Number:");
		member.setPersonalNumber((sc.nextLine()));
		writer.write(String.format("%-15s", member.getPersonalNumber()));
		writer.write(String.format("%15s", memberId.generateMemberId()));
		writer.close();
	}
	
	// check the head of table if exist, else print it.
	private void checkFile() {
		try {
			if(reader.readLine() == null) {
				printHeadOfTable();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	public void printHeadOfTable() throws IOException {
		writer.write("Members\t\t\tPersonal Numbers\t\t\tID\t\t\tBoat Type\t\t\tBoat Length");
	}
}
