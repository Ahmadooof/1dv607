package view;

import model.IPersistence;
import model.Member;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class View implements IView {

    IPersistence storage;

    public View(IPersistence storage) {
        this.storage = storage;
    }

    @Override
    public void printWelcome() {
        System.out.println("Hi and welcome to the club");
        System.out.println("1.Membership.");
        System.out.println("2.register boat.");
        System.out.println("3.view compact list");
    }

    @Override
    public void printBoatIssue() {
        System.out.println("Welcome to Register Boat Menu");
        System.out.println("Press 1 For Existing Member");
        System.out.println("Press 2 For New Member");
    }

    @Override
    public void printExistingMember() {
        System.out.println("Press 1 to Register a New Boat");
        System.out.println("Press 2 to Delete a Boat");
        System.out.println("Press 3 to Change Boat Information");
    }

    @Override
    public void AskForBoatType() {
        System.out.println("Enter Your Boat Type");
    }

    @Override
    public void AskForBoatLength() {
        System.out.println("Enter your Boat length in m");
    }


    @Override
    public void printMembershipIssue() {
        System.out.println("1.Create New Member"
                + "\n2.Retrive Member info"
                + "\n3.Update a Member"
                + "\n4.Delete a Member");
    }

    @Override
    public void askForName() {
        System.out.println("Enter your name: ");
    }

    @Override
    public void askForPersonalNumber() {
        System.out.println("Enter your personal number: ");
    }

    @Override
    public void askForMemberId() {
        System.out.println("Please enter member ID:");
    }

    @Override
    public void printUserNotFound() {
        System.out.println("User not found.");
    }

    @Override
    public void printUserInfo(Member memberInfo) {
        System.out.println("user Info:\n"
                + "Name: " + memberInfo.getName()
                + "\nPerosnalNumber: " + memberInfo.getPersonalNumber()
                + "\nID: " + memberInfo.getId());
    }

    public void userRequest() throws IOException, ParseException {
        Member member = new Member(this.storage);
        switch (menuOption()) {
            case MEMBERSHIP_ISSUE:
                printMembershipIssue();
                switch (membershipOption()) {
                    case REGISTER_MEMBER:
                        askForName();
                        member.setName(userInputString());
                        askForPersonalNumber();
                        member.setPersonalNumber(userInputNumber());
                        member.setId(this.storage.generateID(member.getPersonalNumber()));
                        member.registerMember(member);
                        System.out.println("The Member has been registered");                 // toDo: put it in method.
                        break;
                    case READ_MEMBER:
                        String id;
                        askForMemberId();
                        if (this.storage.isMemberExist(id = userInputString())) {
                            Member memberFound = this.storage.retrieveMemberById(id);
                            System.out.println(memberFound.toString());
                            break;
                        }
                        System.out.println("Member is not found.");                           // toDo: put it in method.
                        break;
                    case UPDATE_MEMBER:
                        askForMemberId();
                        if (this.storage.isMemberExist(id = userInputString())) {
                            Member memberFound = this.storage.retrieveMemberById(id);
                            System.out.println("You are going to update this " + memberFound.toString());
                            System.out.println("Enter new name of the member:");              // toDo: put it in method.
                            memberFound.setName(userInputString());
                            System.out.println("Enter new personal number of the member:");   // toDo: put it in method.
                            memberFound.setPersonalNumber(userInputNumber());
                            memberFound.setId(this.storage.generateID(memberFound.getPersonalNumber()));
                            this.storage.removeMemberById(id);
                            this.storage.updateMember(memberFound);
                            break;
                        }
                        System.out.println("Member is not found.");
                        break;
                    case DELETE_MEMBER:
                        askForMemberId();
                        if (this.storage.isMemberExist(id = userInputString())) {
                            this.storage.removeMemberById(id);
                            System.out.println("Member has been removed");
                            break;
                        }
                        System.out.println("Member is not found.");
                        break;
                }
                break;
            case BOAT_ISSUE:
                printBoatIssue();
        }
    }


    public String userInputString() {
        return new Scanner(System.in).nextLine();
    }

    public int userInputNumber() {
        return new Scanner(System.in).nextInt();
    }

    @Override
    public MenuOption menuOption() {
        switch (userInputNumber()) {
            case 1:
                return MenuOption.MEMBERSHIP_ISSUE;
            case 2:
                return MenuOption.BOAT_ISSUE;
            default:
                return null;
        }
    }

    @Override
    public MenuOption membershipOption() {
        switch (userInputNumber()) {
            case 1:
                return MenuOption.REGISTER_MEMBER;
            case 2:
                return MenuOption.READ_MEMBER;
            case 3:
                return MenuOption.UPDATE_MEMBER;
            case 4:
                return MenuOption.DELETE_MEMBER;
            default:
                System.out.println("Please press a number!");
                return null;
        }
    }

}