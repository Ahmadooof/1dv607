import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import model.member;
import view.console;

public class start {

	public static void main(String[] args) throws IOException {
		console view = new console();
		view.printWelcome();
		view.checkInput();
		view.registerMember();
	}

}
