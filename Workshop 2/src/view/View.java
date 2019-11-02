package view;

import model.Boat;
import model.IPersistence;
import model.Member;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class View implements IView {

    private IPersistence storage;
    private Member member = new Member();
    private Boat boat = new Boat();

    public View(IPersistence storage) {
        this.storage = storage;
    }

    @Override
    public void printWelcome() {
        System.out.println("Hi and welcome to the club");
        System.out.println("1.Membership issue.");
        System.out.println("2.Boat issue.");
        System.out.println("3.View compact list");
        System.out.println("4.View verbose list");
        System.out.println("5.Exit");
    }

    @Override
    public void printBoatMenu() {
        System.out.println("1.Register a new boat");
        System.out.println("2.Update boat information");
        System.out.println("3.Delete a boat");
    }

    @Override
    public void AskForBoatType() {
        System.out.println("Please enter boat type");
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

    public void printBoatType() {
        System.out.println("1.SAILBOAT\n" +
                "2.MOTORSAILER\n" +
                "3.KAYAK\n" +
                "4.CANOE\n" +
                "5.OTHER");
    }

    public void printFileNotFound() {
        System.out.println("File is not found, please register a member to generate the file.");
    }

    /**
     * this method is the start of the program.
     *
     * @throws IOException
     */
    public void userRequest() throws IOException {
        String memberId;
        int boatId;
        Member memberFound;
        switch (menuOption()) {
            case MEMBERSHIP_ISSUE:
                printMembershipIssue();
                switch (membershipOption()) {
                    case REGISTER_MEMBER:
                        registerMember();
                        break;
                    case READ_MEMBER:
                        memberFound = readMember();
                        if (memberFound != null) {
                            System.out.println(
                                    "Name: " + memberFound.getName()
                                            + "\nId:" + memberFound.getId()
                                            + "\nPersonal Number: "
                                            + memberFound.getPersonalNumber()
                            );
                        }
                        break;
                    case UPDATE_MEMBER:
                        memberFound = readMember();
                        if (memberFound != null) {
                            updateMember(memberFound);
                        }
                        break;
                    case DELETE_MEMBER:
                        memberFound = readMember();
                        if (memberFound != null) {
                            this.storage.removeMember(memberFound);
                        }
                        break;
                }
                break;
            case BOAT_ISSUE:
                printBoatMenu();
                switch (boatOption()) {
                    case REGISTER_BOAT:
                        askForMemberId();
                        try {
                            if (this.storage.isMemberExist(memberId = userInputString())) {
                                Member memberFound2 = this.storage.retrieveMemberById(memberId);
                                AskForBoatType();
                                printBoatType();
                                switch (boatTypeOption()) {
                                    case SAILBOAT:
                                        boat.setType("Sailboat");
                                        break;
                                    case MOTORSAILER:
                                        boat.setType("Motorsailer");
                                        break;
                                    case CANOE:
                                        boat.setType("Canoe");
                                        break;
                                    case KAYAK:
                                        boat.setType("Kayak");
                                        break;
                                    case OTHER:
                                        System.out.println("Please enter your boat type: ");
                                        boat.setType(userInputString());
                                        break;
                                }
                                System.out.println("please enter boat length");
                                boat.setLength(userInputNumber());
                                boat.setId(this.storage.generateIDForBoat(memberId));
//                                this.storage.removeMemberById(memberId);
                                this.storage.registerBoat(memberFound2, boat);
                                break;
                            }
                        } catch (Exception e) {
                            printFileNotFound();
                        }
                        System.out.println("Member is not found.");
                        break;
                    case UPDATE_BOAT:
                        askForMemberId();
                        try {
                            if (this.storage.isMemberExist(memberId = userInputString())) {
                                Member memberFound3 = this.storage.retrieveMemberById(memberId);
                                System.out.println("please enter boat ID:");
                                if (this.storage.isBoatExist(boatId = userInputNumber())) {
                                    this.storage.removeBoatById(boatId);
                                    System.out.println("please enter boat type:");
                                    printBoatType();
                                    switch (boatTypeOption()) {
                                        case SAILBOAT:
                                            boat.setType("Sailboat");
                                            break;
                                        case MOTORSAILER:
                                            boat.setType("Motorsailer");
                                            break;
                                        case CANOE:
                                            boat.setType("Canoe");
                                            break;
                                        case KAYAK:
                                            boat.setType("Kayak");
                                            break;
                                        case OTHER:
                                            AskForBoatType();
                                            boat.setType(userInputString());
                                            break;
                                    }
                                    System.out.println("please enter boat length");
                                    boat.setLength(userInputNumber());
                                    boat.setId(this.storage.generateIDForBoat(memberId));
                                    this.storage.updateBoat(boat, memberFound3);
                                    break;
                                }
                                System.out.println("Boat is not found");
                            }
                        } catch (Exception e) {
                            printFileNotFound();
                        }
                        System.out.println("Member is not found");
                        break;
                    case DELETE_BOAT:
                        askForMemberId();
                        try {
                            if (this.storage.isMemberExist(userInputString())) {
                                System.out.println("please enter boat ID:");
                                if (this.storage.isBoatExist(boatId = userInputNumber())) {
                                    this.storage.removeBoatById(boatId);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            printFileNotFound();
                        }
                }
                break;
            case COMPACT_LIST:
                try {
//                    this.storage.loadMembers().getMemberList();
                } catch (Exception e) {
                    System.out.println("File is not found, please register a member to generate the file.");
                }
//                Iterator<Member> iter = this.storage.loadMembers().getMemberList().iterator();
                System.out.println("File is not found, please register a member to generate the file.");

                System.out.format("%-15s%-15s%-15s\n"
                        , "Name", "MemberId", "Number of Boats");
//                while (iter.hasNext()) {
//                    member = iter.next();
                System.out.format("%-15s%-15s%-15s\n"
                        , member.getName()
                        , member.getId()
                        , member.getBoatList().size());
//                }
                break;
            case VERBOSE_LIST:
                try {
//                    this.storage.loadMembers().getMemberList();
                } catch (Exception e) {
                    System.out.println("File is not found, please register a member to generate the file.");
                }
//                Iterator<Member> iterateMember = this.storage.loadMembers().getMemberList().iterator();
                Iterator<Boat> iterateBoat;
                System.out.format(
                        "%20s%20s%20s%20s%20s%20s%20s\n"
                        , "Name", "Personal Number", "Member ID", "Number of Boats"
                        , "Boat Type", "Boat Length", "Boat ID");
//                while (iterateMember.hasNext()) {
                int countNumberBoats = 1;
//                    member = iterateMember.next();
                iterateBoat = member.getBoatList().iterator();
                System.out.format("%20s%20s%20s%20s"
                        , member.getName()
                        , member.getPersonalNumber()
                        , member.getId()
                        , member.getBoatList().size());
                if (!iterateBoat.hasNext()) {
                    System.out.println();
                    for (int i = 0; i < 140; i++)
                        System.out.print("-");
                    System.out.println();
                }
                while (iterateBoat.hasNext()) {
                    boat = iterateBoat.next();
                    if (countNumberBoats == 1) {
                        System.out.format("%20s%20s%20s\n"
                                , boat.getType(), boat.getLength(), boat.getId());
                        countNumberBoats++;
                    } else {
                        System.out.format("%100s%20s%20s\n"
                                , boat.getType(), boat.getLength(), boat.getId());
                    }
                    if (!iterateBoat.hasNext()) {
                        for (int i = 0; i < 140; i++)
                            System.out.print("-");
                        System.out.println();
                    }
                }
//                }
                break;
            case Exit:
                System.out.println("Thanks for using our service");
                System.exit(0);
        }
        System.out.println("Everything is updated.");
        System.out.println("Press any key to restart, Number 5 To Exit.");
    }

    private void updateMember(Member memberFound) throws IOException {
        System.out.println("Enter new name of the member:");
        member.setName(userInputString());
        System.out.println("Enter new personal number of the member:");
        member.setPersonalNumber(userInputNumber());
        member.setId(this.storage.generateID(member.getPersonalNumber()));
        this.storage.removeMember(memberFound);
        this.storage.saveMember(member);
    }

    private Member readMember() throws IOException {
        askForMemberId();
        String memberId = userInputString();
        Member memberFound = this.storage.retrieveMemberById(memberId);
        if (memberFound == null) {
            System.out.println("Member is not found!");
            return null;
        } else {
            return memberFound;
        }
    }

    private void registerMember() throws IOException {
        askForName();
        member.setName(userInputString());
        askForPersonalNumber();
        member.setPersonalNumber(userInputNumber());
        member.setId(this.storage.generateID(member.getPersonalNumber()));
        this.storage.saveMember(member);
    }

    @Override
    public String userInputString() {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public Integer userInputNumber() {
        return new Scanner(System.in).nextInt();
    }

    public MenuOption boatTypeOption() {
        try {
            switch (userInputNumber()) {
                case 1:
                    return MenuOption.SAILBOAT;
                case 2:
                    return MenuOption.MOTORSAILER;
                case 3:
                    return MenuOption.KAYAK;
                case 4:
                    return MenuOption.CANOE;
                case 5:
                    return MenuOption.OTHER;
                default:
                    System.out.println("please choose a boat by press number from 1 to 5");
                    return boatTypeOption();
            }
        } catch (Exception e) {
            System.out.println("please press a number not characters");
            return boatTypeOption();
        }
    }

    @Override
    public MenuOption menuOption() {
        try {
            switch (userInputNumber()) {
                case 1:
                    return MenuOption.MEMBERSHIP_ISSUE;
                case 2:
                    return MenuOption.BOAT_ISSUE;
                case 3:
                    return MenuOption.COMPACT_LIST;
                case 4:
                    return MenuOption.VERBOSE_LIST;
                case 5:
                    return MenuOption.Exit;
                default:
                    System.out.println("please choose a number from 1 to 5");
                    return menuOption();
            }
        } catch (Exception e) {
            System.out.println("please press a number");
            return menuOption();
        }
    }


    @Override
    public MenuOption membershipOption() {
        try {
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
                    System.out.println("please choose an option by press number from 1 to 4");
                    return membershipOption();
            }
        } catch (Exception e) {
            System.out.println("please press a number not character.");
            return membershipOption();
        }
    }

    public MenuOption boatOption() {
        try {
            switch (userInputNumber()) {
                case 1:
                    return MenuOption.REGISTER_BOAT;
                case 2:
                    return MenuOption.UPDATE_BOAT;
                case 3:
                    return MenuOption.DELETE_BOAT;
                default:
                    System.out.println("please choose an option by press number from 1 to 3");
                    return boatOption();
            }
        } catch (Exception e) {
            System.out.println("please press number not character");
            return boatOption();
        }
    }

}