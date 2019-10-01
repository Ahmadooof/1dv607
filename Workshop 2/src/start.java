import java.io.IOException;

import controller.ControlManager;
import view.GeneralConsole;

public class start {

	public static void main(String[] args) throws IOException {

		view.GeneralConsole view = new GeneralConsole();
		controller.ControlManager control = new ControlManager(); 

		view.printWelcome();
		control.userChoice();
	}

}
