package model;

import java.util.ArrayList;
import java.util.List;

public class MemberList {
    private List<Member> memberList = new ArrayList<Member>();

    public MemberList(){

    }

    public MemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

}
