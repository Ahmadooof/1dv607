package view;

import model.Member;

public class MembershipConsole {

	public void printMemberChoices() {
		System.out.println("1.Create New Member"
				+ "\n2.Retrive Member info"
				+ "\n3.Delete a Member"
				+ "\n4.Delete a Member");
	}
	
	public void askForName() {
		System.out.println("Enter your name: ");
	}
	
	public void askForPersonalNumber() {
		System.out.println("Enter your personal number: ");
	}

	public void askForMemberId(){
		System.out.println("Please enter member ID:");
	}

	public void printUserNotFound() {
		System.out.println("User not found.");
	}

	public void printUserInfo(Member memberInfo) {
		System.out.println("user Info:\n"
				+"Name: "+memberInfo.getName()
				+"\nPerosnalNumber: "+memberInfo.getPersonalNumber()
				+"\nID: "+memberInfo.getId());
	}

}
