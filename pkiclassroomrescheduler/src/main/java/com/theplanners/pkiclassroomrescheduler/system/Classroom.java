package com.theplanners.pkiclassroomrescheduler.system;

import java.util.Arrays;

//Room,Seats,Computers,Information,Connectivity,Displays
public class Classroom {
    private int room;
    private int seats;
    private int computers;
    private String information;
    private String[] connectivity;
    private String[] displays;
    public Classroom(int room, int seats, int computers, String information, String[] connectivity, String[] displays) {
        this.room = room;
        this.seats = seats;
        this.computers = computers;
        this.information = information;
        this.connectivity = connectivity;
        this.displays = displays;
    }
    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public int getComputers() {
        return computers;
    }
    public void setComputers(int computers) {
        this.computers = computers;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String[] getConnectivity() {
        return connectivity;
    }
    public void setConnectivity(String[] connectivity) {
        this.connectivity = connectivity;
    }
    public String[] getDisplays() {
        return displays;
    }
    public void setDisplays(String[] displays) {
        this.displays = displays;
    }
    @Override
    public String toString() {
        return "Classroom [room=" + room + ", seats=" + seats + ", computers=" + computers + ", information="
                + information + ", connectivity=" + Arrays.toString(connectivity) + ", displays="
                + Arrays.toString(displays) + "]";
    }
}
