package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

import Controller.member;
import model.Id;

public class console {
	BufferedWriter writer;
	BufferedReader reader;
	Scanner sc = new Scanner(System.in);
	int input;
	static int id;

	public console() throws IOException {
		writer = new BufferedWriter(new FileWriter("members.txt", true));
		reader= new BufferedReader(new FileReader("C:\\Users\\ahmad\\Desktop\\eclipse-workspace\\Workshop 2\\members.txt"));
	}

	public void printWelcome() {
		System.out.println("Hi and weclome to the club");
		System.out.println("1.register member.");
		System.out.println("2.register boat.");
		System.out.println("3.view compact list");
	}

	public void checkInput() {
		this.input = sc.nextInt();
	}

	public void registerMember(Id memberId, member member) throws IOException {
		checkFile();
		writer.write("\n");
		System.out.println("Enter your name:");
		member.setName(sc.next());
		writer.write(String.format("%-16s", member.getName()));
		System.out.println("Enter your personal Number:");
		member.setPersonalNumber((sc.nextInt()));
		writer.write(String.format("%-15s", member.getPersonalNumber()));
		memberId.generateMemberId();
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

	private void printHeadOfTable() throws IOException {
		writer.write("Members\t\t\tPersonal Numbers\t\t\tID");
	}
}
