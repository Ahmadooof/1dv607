package view;

import java.io.IOException;
import java.util.Scanner;

import model.Boat;

public class BoatConsole {
	public void printBoatMenu() {
		System.out.println("Welcome to Register Boat Menu");
		System.out.println("Press 1 For Existing Member");
		System.out.println("Press 2 For New Member");
	}

	public void printExistingMember() {
		System.out.println("Press 1 to Register a New Boat");
		System.out.println("Press 2 to Delete a Boat");
		System.out.println("Press 3 to Change Boat Information");
	}

	public void AskForBoatType(){
		System.out.println("Enter Your Boat Type");
	}

	public void AskForBoatLength(){
		System.out.println("Enter your Boat length in m");
	}

}
