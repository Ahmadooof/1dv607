import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import Controller.member;
import model.Id;
import view.console;

public class start {

	public static void main(String[] args) throws IOException {
		
		view.console view = new console();
		model.Id memberId = new Id();
		Controller.member member = new member(); 
		
		view.printWelcome();
		member.checkUserInput(view, memberId);
		
	}

}
