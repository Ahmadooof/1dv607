package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Id {

	BufferedReader reader;
	public Id() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader("C:\\Users\\ahmad\\Desktop\\eclipse-workspace\\Workshop 2\\members.txt"));
	}

	// generating unique ID for member by counting members
	public int generateMemberId() throws IOException {
		int countMembers = 0;
		while(reader.readLine() != null) {
			countMembers++;
		}
		if(countMembers == 0)
			return 1;
		else 
			return countMembers;
	}

}
