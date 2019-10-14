package view;

import model.Member;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IView {
    void printWelcome();

    void printBoatMenu();

    void AskForBoatType();

    void AskForBoatLength();

    void printMembershipIssue();

    void askForName();

    void askForPersonalNumber();

    void askForMemberId();

    void printUserNotFound();

    void printUserInfo(Member memberInfo);

    void userRequest() throws IOException, ParseException;

    MenuOption membershipOption();

    MenuOption menuOption();

}
