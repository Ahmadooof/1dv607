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
     * @param newMember
     * @throws IOException
     */
    @Override
    public void saveMember(Member newMember) throws IOException {
        this.memberListObject.getMemberList().add(newMember);
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
     * @param memberFound
     * @param newBoat
     * @throws IOException
     */
    public void registerBoat(Member memberFound, Boat newBoat) throws IOException {
        memberFound.getBoatList().add(newBoat);
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
