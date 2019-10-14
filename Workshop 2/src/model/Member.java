package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int personalNumber;
    private String id;

    private IPersistence storage;
    private List<Boat> boatList;

    public Member() {}

    public Member(IPersistence storage) {
        this.storage = storage;
        this.boatList = new ArrayList<Boat>();
    }

    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void registerMember(Member newMember) throws IOException {
        this.storage.saveMember(newMember);
    }

    @Override
    public String toString() {
        return "Member: " +
                "name= '" + name + '\'' +
                ", personalNumber= " + personalNumber +
                ", id= '" + id + '\'';
    }
}
