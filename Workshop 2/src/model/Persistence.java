package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Persistence implements IPersistence {

    List<Member> memberList;
    MemberList memberListObject;
    ObjectMapper om;
    File file;

    public Persistence() throws IOException {
        file = new File("members.json");
        memberList = new ArrayList<Member>();
        memberListObject = new MemberList(memberList);
        om = new ObjectMapper();
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
     * this method generate unique id by counting boats for specific member
     * adding +1 after counting boats.
     *
     * @param memberID
     * @return 5 if member has 4 boats.
     * @throws IOException
     */
    @Override
    public int generateIDForBoat(String memberID) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            if (currentMembers.get(i).getId().equals(memberID))
                return currentMembers.get(i).getBoatList().size() + 1;
        }
        return -1;        // there are no members yet
    }

    /**
     * @return return an object of array list of members.
     * @throws IOException
     */
    @Override
    public MemberList loadMembers() throws IOException {
        memberListObject = om.readerFor(MemberList.class).readValue(new File("members.json"));
        return memberListObject;
    }

    /**
     * @param newMember
     * @return
     * @throws IOException
     */
    @Override
    public boolean saveMember(Member newMember) throws IOException {
        if (file.length() == 0) {
            memberList.add(newMember);
        } else {
            memberListObject.setMemberList(loadMembers().getMemberList());
            memberListObject.getMemberList().add(newMember);
        }
        om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
        return true;
    }

    /**
     * @param searchForId
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public boolean isMemberExist(String searchForId) throws IOException, ParseException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        System.out.println(currentMembers.size());
        for (int i = 0; i < memberListObject.getMemberList().size(); i++) {
            if (currentMembers.get(i).getId().equals(searchForId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param id
     * @return true if we found the member which we need to remove by id
     * @throws IOException
     */
    public boolean removeMemberById(String id) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            if (currentMembers.get(i).getId().equals(id)) {
                currentMembers.remove(i);
                memberListObject.setMemberList(currentMembers);
                om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
                return true;
            }
        }
        return false;
    }

    /**
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public Member retrieveMemberById(String id) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            if (currentMembers.get(i).getId().equals(id)) {
                return currentMembers.get(i);
            }
        }
        return null;
    }

    /**
     * @param updateMember
     * @return
     * @throws IOException
     */
    @Override
    public boolean updateMember(Member updateMember) throws IOException {
        memberListObject.getMemberList().add(updateMember);
        om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
        return true;
    }

    /**
     * @param memberFound
     * @param newBoat
     * @return
     * @throws IOException
     */
    public boolean registerBoat(Member memberFound, Boat newBoat) throws IOException {
        memberFound.getBoatList().add(newBoat);
        memberListObject.setMemberList(loadMembers().getMemberList());
        memberListObject.getMemberList().add(memberFound);
        om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
        return true;
    }

    /**
     * @param boatID
     * @return
     * @throws IOException
     */
    @Override
    public boolean isBoatExist(int boatID) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            for (int j = 0; j < currentMembers.get(i).getBoatList().size(); j++) {
                if (currentMembers.get(i).getBoatList().get(j).getId() == (boatID)) {
                    return true;
                }
            }
        }
        return false;        // there is no boat for this id.
    }

    /**
     * @param searchBoatID
     * @throws IOException
     */
    @Override
    public void removeBoatById(int searchBoatID) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            for (int j = 0; j < currentMembers.get(i).getBoatList().size(); j++) {
                if (currentMembers.get(i).getBoatList().get(j).getId() == searchBoatID) {
                    currentMembers.get(i).getBoatList().remove(j);
                    memberListObject.setMemberList(currentMembers);
                    om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
                }
            }
        }
    }

    /**
     * @param updateBoat
     * @param memberFound
     * @throws IOException
     */
    @Override
    public void updateBoat(Boat updateBoat, Member memberFound) throws IOException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < currentMembers.size(); i++) {
            if (currentMembers.get(i).getId().equals(memberFound.getId())) {
                currentMembers.get(i).getBoatList().add(updateBoat);
                memberListObject.setMemberList(currentMembers);
                om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
            }
        }
    }
}
