import java.io.IOException;

import model.IPersistence;
import model.Persistence;
import org.json.simple.parser.ParseException;
import view.IView;
import view.View;

public class start {

	public static void main(String[] args) throws IOException, ParseException {
		IPersistence storage = new Persistence();		//Model
		IView view = new View(storage);				//View
		view.printWelcome();
		view.userRequest();
	}

}
