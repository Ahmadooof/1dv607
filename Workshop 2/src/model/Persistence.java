package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
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
     * this method takes personalNumber as uniqueID and
     * increasing each character by 1 which still unique.
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
                System.out.println("hi");
            } else {
                c++;
            }
            uniqueId.append(c);
        }
        String unique = uniqueId.toString();
        return unique;
    }

    @Override
    public MemberList loadMembers() throws IOException {
        memberListObject = om.readerFor(MemberList.class).readValue(new File("members.json"));
        return memberListObject;
    }

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

    @Override
    public boolean isMemberExist(String searchForId) throws IOException, ParseException {
        memberListObject = loadMembers();
        ArrayList<Member> currentMembers = (ArrayList<Member>) memberListObject.getMemberList();
        for (int i = 0; i < memberListObject.getMemberList().size(); i++) {
            if (currentMembers.get(i).getId().equals(searchForId)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeMemberById(String id) throws IOException {
        memberListObject = loadMembers();
        for (int i = 0; i < memberListObject.getMemberList().size(); i++) {
            if (memberListObject.getMemberList().get(i).getId().equals(id)) {
                memberListObject.getMemberList().remove(i);
                return true;
            }
        }
        return false;
    }

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

    @Override
    public boolean updateMember(Member updateMember) throws IOException {
        memberListObject.getMemberList().add(updateMember);
        om.writerWithDefaultPrettyPrinter().writeValue(file, memberListObject);
        return true;
    }
}
