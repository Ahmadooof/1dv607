package controller;

import java.io.IOException;

import model.Member;
import view.MembershipConsole;

public class MemberControl extends ControlManager {
	MembershipConsole memberConsole;
	Member memberModel;
	
	public MemberControl() throws IOException {
		memberModel = new Member();
		memberConsole = new MembershipConsole();
	}

	public void membershipIssue() throws IOException {
		memberConsole.printMemberChoices();
		switch(userInput()) {
			case "1":
				registerMember();
			break;

			case "2":
			memberConsole.askForMemberId();
			memberModel.setId(userInput());
			if(	(memberModel = memberModel.retrieveMemberByID(memberModel)) == null	)
				memberConsole.printUserNotFound();
			else
				memberConsole.printUserInfo(memberModel);
			break;

			case "3":
			memberConsole.askForMemberId();
			memberModel.setId(userInput());
			if(	(memberModel = memberModel.retrieveMemberByID(memberModel)) == null	)
				memberConsole.printUserNotFound();
			else
				memberModel.removeMemberById(memberModel);
			break;
		}
	}

	public void registerMember() throws IOException {
		memberConsole.askForName();
		memberModel.setName(userInput());
		memberConsole.askForPersonalNumber();
		memberModel.setPersonalNumber(userInput());
		memberModel.registerMember(memberModel);
	}
}
