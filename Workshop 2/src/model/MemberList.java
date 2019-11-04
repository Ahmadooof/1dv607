package model;

import java.util.ArrayList;
import java.util.List;

class MemberList {

    private List<Member> memberList;

    MemberList() {
        memberList = new ArrayList<Member>();
    }

    public List<Member> getMemberList() {
        return memberList;
    }

}
