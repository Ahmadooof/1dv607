package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Persistence implements IPersistence {

    private MemberList memberListObject;
    private ObjectMapper om;
    private File file;

    public Persistence() throws IOException {
        file = new File("members.json");
        memberListObject = new MemberList();
        om = new ObjectMapper();
        if (file.length() != 0) {
            memberListObject = om.readerFor(MemberList.class).readValue(new File("members.json"));
        }
    }

    /**
     * this method generates read each character for personal number
     * and adding 1 to each number to make it unique.
     * (we assume that personal number is unique id :) )
     *
     * @param personalNumber
     * @return
     */
    @Override
    public String generateID(int personalNumber) {
        String numberString = Integer.toString(personalNumber);
        StringBuilder uniqueId = new StringBuilder();
        for (int i = 0; i < numberString.length(); i++) {
            char c = numberString.charAt(i);
            if (c == '9') {
                c = 'a';
            } else {
                c++;
            }
            uniqueId.append(c);
        }
        return uniqueId.toString();
    }

    /**
     * this method generates boat id by counting the number of boats which member has.
     *
     * @param memberFound
     * @return
     * @throws IOException
     */
    @Override
    public int generateIDForBoat(Member memberFound) throws IOException {
        return memberFound.getBoatList().size() + 1;
    }

    /**
     * @param name
     * @param personalNumber
     * @throws IOException
     */
    @Override
    public void saveMember(String name, int personalNumber) throws IOException {
        Member a_member = new Member();
        a_member.setName(name);
        a_member.setPersonalNumber(personalNumber);
        a_member.setId(generateID(a_member.getPersonalNumber()));
        this.memberListObject.getMemberList().add(a_member);
    }

    @Override
    public void saveMember(String name, int personalNumber, Member memberFound) throws IOException {
        Member a_member = new Member();
        a_member.setName(name);
        a_member.setPersonalNumber(personalNumber);
        a_member.setId(generateID(a_member.getPersonalNumber()));
        a_member.setBoatList(memberFound.getBoatList());
        this.memberListObject.getMemberList().add(a_member);
    }

    /**
     * @param memberFound
     * @return
     * @throws IOException
     */
    public void removeMember(Member memberFound) throws IOException {
        this.memberListObject.getMemberList().remove(memberFound);
    }

    /**
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public Member retrieveMemberById(String id) throws IOException {
        for (int i = 0; i < memberListObject.getMemberList().size(); i++) {
            if (memberListObject.getMemberList().get(i).getId().equals(id)) {
                return memberListObject.getMemberList().get(i);
            }
        }
        return null;
    }

    /**
     * @param boatType
     * @param boatLength
     * @param memberFound
     * @throws IOException
     */
    public void registerBoat(Boat.Type boatType, int boatLength, Member memberFound) throws IOException {
        Boat a_boat = new Boat();
        a_boat.setLength(boatLength);
        a_boat.setType(boatType);
        a_boat.setId(generateIDForBoat(memberFound));
        memberFound.getBoatList().add(a_boat);
    }

    /**
     * @param boatId
     * @return
     * @throws IOException
     */
    @Override
    public boolean removeBoatById(int boatId, Member memberFound) throws IOException {
        for (int j = 0; j < memberFound.getBoatList().size(); j++) {
            if (memberFound.getBoatList().get(j).getId() == boatId) {
                memberFound.getBoatList().remove(j);
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Member> iterateMembers() {
        return this.memberListObject.getMemberList().iterator();
    }

    @Override
    public Iterator<Boat> iterateBoats(Member member) {
        return member.getBoatList().iterator();
    }

    @Override
    public void writeToFile() {
        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
