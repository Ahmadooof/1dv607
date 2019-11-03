package model;

import java.io.IOException;
import java.util.Iterator;

public interface IPersistence {

    String generateID(int personalNumber);

    int generateIDForBoat(Member memberFound) throws IOException;

    void saveMember(Member newMember) throws IOException;

    Member retrieveMemberById(String id) throws IOException;

    void removeMember(Member memberFound) throws IOException;

    void registerBoat(Member memberFound, Boat newBoat) throws IOException;

    boolean removeBoatById(int boatID, Member memberFound) throws IOException;

    Iterator<Member> iterateMembers();

    Iterator<Boat> iterateBoats(Member member);

    void writeToFile();
}
