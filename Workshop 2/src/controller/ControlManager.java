package controller;

import java.io.IOException;
import java.util.Scanner;

public class ControlManager implements IOUser {

	public void userChoice() throws IOException {
		MemberControl memberControl = new MemberControl();
		BoatControl boatControl = new BoatControl();
		switch (userInput()) {
		case "1":
			memberControl.membershipIssue();
			break;
		case "2":
			boatControl.boatIssue();
			break;
		case "3":
			System.out.println("hi");
			break;
		}
	}

	@Override
	public String userInput() {
		Scanner userInput = new Scanner(System.in);
		return userInput.nextLine();
	}

}
