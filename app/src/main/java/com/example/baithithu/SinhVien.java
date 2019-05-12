package com.example.baithithu;

public class SinhVien {
    private int mID;
    private String ID;
    private String Name;

    public SinhVien(int mID, String ID, String name) {
        this.mID = mID;
        this.ID = ID;
        Name = name;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
