package com.example.demo200000.enity;

public class Groups {
    private int groupid;

    private String groupname;

    private String sporttype;

    private String aage;

    private int maxstudents;

    private int coachesid;

    public Groups(int groupid, String groupname, String sporttype,
                  String aage, int maxstudents, int coachesid) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.sporttype = sporttype;
        this.aage = aage;
        this.maxstudents = maxstudents;
        this.coachesid = coachesid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getSporttype() {
        return sporttype;
    }

    public void setSporttype(String sporttype) {
        this.sporttype = sporttype;
    }

    public String getAage() {
        return aage;
    }

    public void setAage(String aage) {
        this.aage = aage;
    }

    public int getMaxstudents() {
        return maxstudents;
    }

    public void setMaxstudents(int maxstudents) {
        this.maxstudents = maxstudents;
    }

    public int getCoachesid() {
        return coachesid;
    }

    public void setCoachesid(int coachesid) {
        this.coachesid = coachesid;
    }
}
