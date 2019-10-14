package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IPersistence {

    String generateID(int personalNumber);

    int generateIDForBoat(String id) throws IOException;

    MemberList loadMembers() throws IOException;

    boolean saveMember(Member newMember) throws IOException;

    boolean isMemberExist(String userInputString) throws IOException, ParseException;

    Member retrieveMemberById(String id) throws IOException;

    boolean updateMember(Member member) throws IOException;

    boolean removeMemberById(String id) throws IOException;

    boolean registerBoat(Member memberFound,Boat newBoat) throws IOException;

    boolean isBoatExist(int i) throws IOException;

    void removeBoatById(int sarchBoatID) throws IOException;

    void updateBoat(Boat boat,Member member) throws IOException;

//    List<Member> getMemberList();

//    void setMemberList(List<Member> memberList);
}
