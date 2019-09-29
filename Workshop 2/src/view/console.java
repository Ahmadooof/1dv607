package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

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

	public void registerMember() throws IOException {
		// check the head of table if exist, else print it.
		try {
			if(reader.readLine() == null) {
				printHeadOfTable();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		writer.write("\n");
		System.out.println("Enter your name:");
		String name = sc.next();
		writer.write(String.format("%-16s", name));
		System.out.println("Enter your personal Number:");
		String personalNumber = sc.next();
		writer.write(String.format("%-15s", personalNumber));

		writer.close();
	}

	private void printHeadOfTable() throws IOException {
		writer.write("Members\t\t\tPersonal Numbers\t\t\tID");
	}
}
