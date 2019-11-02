package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IPersistence {

    String generateID(int personalNumber);

    int generateIDForBoat(String id) throws IOException;

    void saveMember(Member newMember) throws IOException;

    boolean isMemberExist(String userInputString) throws IOException, ParseException;

    Member retrieveMemberById(String id) throws IOException;

//    void updateMember(Member member) throws IOException;

    void removeMember(Member memberFound) throws IOException;

    boolean registerBoat(Member memberFound, Boat newBoat) throws IOException;

    boolean isBoatExist(int i) throws IOException;

    void removeBoatById(int sarchBoatID) throws IOException;

    void updateBoat(Boat boat, Member member) throws IOException;

}
