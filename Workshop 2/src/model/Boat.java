package model;

public class Boat {

    private Type type;
    private int length;
    private int id;
    public Boat() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        SAILBOAT,
        MOTORSAILER,
        KAYAK,
        CANOE,
        OTHER,
    }
}

