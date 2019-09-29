package view;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class console {

	Scanner sc = new Scanner(System.in);
	int input;
	static int id;
	
	public void printWelcome() {
		System.out.println("Hi and weclome to the club");
		System.out.println("1.register member.");
		System.out.println("2.register boat.");
		System.out.println("3.view compact list");
	}

	public void checkInput() {
			this.input = sc.nextInt();
	}

	public void registerMember() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("members.txt", true));
		System.out.println("Enter your name:");
		String name = sc.next();
		writer.write(String.format("%-15s", name));
		System.out.println("Enter your personal Number:");
		String personalNumber = sc.next();
		writer.write(String.format("%-15s", personalNumber));
		writer.close();
	}
}
