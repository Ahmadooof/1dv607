package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IPersistence {

    String generateID(int personalNumber);

    MemberList loadMembers() throws IOException;

    boolean saveMember(Member newMember) throws IOException;

    boolean isMemberExist(String userInputString) throws IOException, ParseException;

    Member retrieveMemberById(String id) throws IOException;

    boolean updateMember(Member member) throws IOException;

    boolean removeMemberById(String id) throws IOException;

    boolean registerBoat(Member memberFound,Boat newBoat) throws IOException;

//    List<Member> getMemberList();

//    void setMemberList(List<Member> memberList);
}
