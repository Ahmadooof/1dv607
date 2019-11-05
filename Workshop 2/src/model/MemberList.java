package model;

import java.util.ArrayList;
import java.util.List;

class MemberList {

    private List<Member> memberList;

    MemberList() {
        memberList = new ArrayList<>();
    }

    public List<Member> getMemberList() {
        return memberList;
    }

}
