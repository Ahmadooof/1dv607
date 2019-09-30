import java.io.IOException;

import Controller.member;
import model.Id;
import view.GeneralConsole;

public class start {

	public static void main(String[] args) throws IOException {

		view.GeneralConsole view = new GeneralConsole();
		model.Id memberId = new Id();
		Controller.member member = new member(); 
		
		view.printWelcome();
		member.userChoice(memberId);
	}

}
