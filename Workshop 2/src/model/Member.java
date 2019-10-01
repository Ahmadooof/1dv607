package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Member extends ReadWriteFile{
	public Member() throws IOException {
		super();
	}

	private String name;
	private String personalNumber;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void registerMember(Member registerMember) throws IOException {
		checkFile();
		Id memberId = new Id();
		writeToFile().write("\n");
		writeToFile().write(String.format("%-16s", registerMember.getName()));
		writeToFile().write(String.format("%-15s", registerMember.getPersonalNumber()));
		writeToFile().write(String.format("%15s", memberId.generateMemberId()));
		writeToFile().close();
	}

	public Member retrieveMemberByID(Member userInput) throws IOException {
		Member searchForMember = new Member();
		String line;
		while(	(line = readFromFile().readLine()) != null) {
			if(line.length() == 31)
				continue;
			searchForMember.setName((String) line.subSequence(0, 15));
			searchForMember.setPersonalNumber((String) line.subSequence(16, 31));
			searchForMember.setId((String)line.subSequence(32, 46));
			searchForMember.setId(searchForMember.getId().replaceAll("\\D+", ""));
			if(searchForMember.getId().equals(userInput.getId())) {
				return searchForMember;
			}
		}
		return null;
	}

	public void removeMemberById(Member memberID) throws IOException {
		String currentLine;
		StringBuffer buff = new StringBuffer();
		Integer countLines = -1;
		while( (currentLine = readFromFile().readLine()) != null ) {
			countLines++;
			if(countLines.toString().equals(memberID.getId()))
				continue;
			buff.append(currentLine);
			buff.append("\n");
		}
		BufferedWriter overWriter = new BufferedWriter(new FileWriter("members.txt",false));
		String addAllLines = buff.toString();
		overWriter.write(addAllLines);

		overWriter.close();
		readFromFile().close();
	}


	private void checkFile() {
		try {
			if(readFromFile().readLine() == null) {
				printHeadOfTable();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void printHeadOfTable() throws IOException {
		writer.write("Members\t\t\tPersonal Numbers\t\t\tID\t\t\tBoat Type\t\t\tBoat Length");
	}
}
