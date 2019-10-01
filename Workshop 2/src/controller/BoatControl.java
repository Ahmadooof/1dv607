package controller;

import model.Boat;
import view.BoatConsole;
import view.MembershipConsole;

import java.io.IOException;

public class BoatControl extends ControlManager{
	BoatConsole boatConsole;
	MemberControl memberControl;
	MembershipConsole memberConsole;
	Boat boatModel;
	
	public BoatControl() throws IOException {
		 boatModel = new Boat();
		 memberConsole = new MembershipConsole();
		 memberControl = new MemberControl();
		 boatConsole = new BoatConsole();
	}

	public void boatIssue() throws IOException {

				boatConsole.printBoatMenu();
				switch(userInput()) {
					case "1":
						boatConsole.printExistingMember();
						switch (userInput()){
							case "1":
								registerBoat();
								break;
							case "2":
								break;
							case "3":
								break;
						}
						break;
					case "2":
						memberControl.registerMember();
						break;
				}
	}

	public void registerBoat() throws IOException {
		memberConsole.askForMemberId();
		boatModel.setId(userInput());
		boatConsole.AskForBoatType();
		boatModel.setType(userInput());
		boatConsole.AskForBoatLength();
		boatModel.setLength(userInput());
		boatModel.registerBoat(boatModel);
	}
}