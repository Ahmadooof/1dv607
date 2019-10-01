package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Id extends ReadWriteFile{
//	BufferedReader reader;
	public Id() throws IOException {
//		super();
//		reader = new BufferedReader(new FileReader("members.txt"));
	}

	// generating unique ID for member by counting members.
	public int generateMemberId() throws IOException {
        Integer countMembers = 0;
        while(readFromFile().readLine() != null) {
			countMembers++;
		}
		if(countMembers == 0) {
			return 1;
		}
		else 
		{
			return countMembers;
		}
	}
}
