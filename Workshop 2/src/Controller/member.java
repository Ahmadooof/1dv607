package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import model.Id;
import view.CreateMember;
import view.GeneralConsole;
import view.MembershipConsole;
import view.RetrieveMember;

public class member {
	private String name;
	private int personalNumber;
	private int id;
	private MembershipConsole memberConsole;
	private CreateMember member;
	BufferedReader reader;
	Scanner input = new Scanner(System.in);

	public member() throws IOException{
		super();
		member = new CreateMember();
		memberConsole = new MembershipConsole();
	}

	//    ****************** Getters And Setters *************

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public void wantsToRegister(GeneralConsole view) {

	}

	public void userChoice(Id memberId) throws IOException {
		RetrieveMember retrieveMember = new RetrieveMember();
		if(input.nextInt() == 1) {
			memberConsole.printMemberChoises();
			switch(input.nextInt()) {
			case 1:
				member.registerMember(memberId,this);
			case 2:
				if(!retrieveMember.retrieveMemberByID(retrieveMember.printRetrieveMember()))
					retrieveMember.printUserNotFound();
			}
		}
	}


}
