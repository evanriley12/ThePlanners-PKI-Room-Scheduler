package com.theplanners.pkiclassroomrescheduler.system;

import java.util.Arrays;

/**
 * Classroom is a class to create an object for the respective classrooms
 * that are found within PKI including their respective attributes.
 */
public class Classroom {
    private int room;
    private int seats;
    private int computers;
    private String information;
    private String[] connectivity;
    private String[] displays;

    /**
     * The constructor for the Classroom class.
     * @param room the integer containing the number for the room located within PKI
     * @param seats the integer containing the number of seats within a given classroom
     * @param computers the integer containing the number of computers within a given classroom
     * @param information the string containing the information for a given classroom
     * @param connectivity the list of strings containing the connectivity of a given classroom
     * @param displays the list of strings containing the displays found within a given classroom
     */
    public Classroom(int room, int seats, int computers, String information, String[] connectivity, String[] displays) {
        this.room = room;
        this.seats = seats;
        this.computers = computers;
        this.information = information;
        this.connectivity = connectivity;
        this.displays = displays;
    }

    /**
     * Gets the room number for a given classroom.
     * @return the integer containing the room number
     */
    public int getRoom() {
        return room;
    }

    /**
     * Sets the room number for a given classroom.
     * @param room the integer containing the desired room number
     */
    public void setRoom(int room) {
        this.room = room;
    }

    /**
     * Gets the number of seats for a given classroom.
     * @return the integer containing the number of seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets the number of seats for a given classroom.
     * @param seats the integer containing the desired number of seats
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Gets the number of computers for a given classroom.
     * @return the integer containing the number of computers
     */
    public int getComputers() {
        return computers;
    }

    /**
     * Sets the number of computers for a given classroom.
     * @param computers the integer containing the desired number of computers
     */
    public void setComputers(int computers) {
        this.computers = computers;
    }

    /**
     * Gets the information for a given classroom.
     * @return the string containing the information for a given classroom
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets the information for a given classroom.
     * @param information the string containing the desired classroom information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets the connectivity for a given classroom.
     * @return the list of strings containing the connectivity for a given classroom
     */
    public String[] getConnectivity() {
        return connectivity;
    }

    /**
     * Sets the connectivity for a given classroom.
     * @param connectivity the list of strings containing the desired connectivity for a given classroom.
     */
    public void setConnectivity(String[] connectivity) {
        this.connectivity = connectivity;
    }

    /**
     * Gets the displays for a given classroom.
     * @return the list of strings containing the displays for a given classroom
     */
    public String[] getDisplays() {
        return displays;
    }

    /**
     * Sets the displays for a given classroom.
     * @param displays the list of strings containing the desired displays for a given classroom
     */
    public void setDisplays(String[] displays) {
        this.displays = displays;
    }

    /**
     * Provides a string listing all the information contained within a Classroom object.
     */
    @Override
    public String toString() {
        return "Classroom [room=" + room + ", seats=" + seats + ", computers=" + computers + ", information="
                + information + ", connectivity=" + Arrays.toString(connectivity) + ", displays="
                + Arrays.toString(displays) + "]";
    }
}
