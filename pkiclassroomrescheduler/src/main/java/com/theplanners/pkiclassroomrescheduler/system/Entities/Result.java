package com.theplanners.pkiclassroomrescheduler.system.Entities;

import java.util.ArrayList;

public class Result {
    private String course;
    private String courseTitle;
    private int sectionNumber;
    private Classroom oldClassroom;
    private Classroom newClassroom;
    private ArrayList<Classroom> otherEqualClassrooms;
    private ArrayList<Classroom> otherWorseClassrooms;

    public Result(Section section, Classroom oldClassroom, Classroom newClassroom, ArrayList<Classroom> otherEqualClassrooms, ArrayList<Classroom> otherWorseClassrooms) {
        this.course = section.getCourse();
        this.courseTitle = section.getCourseTitle();
        this.sectionNumber = section.getSectionNumber();
        this.oldClassroom = oldClassroom;
        this.newClassroom = newClassroom;
        this.otherEqualClassrooms = otherEqualClassrooms;
        this.otherWorseClassrooms = otherWorseClassrooms;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Classroom getOldClassroom() {
        return oldClassroom;
    }

    public void setOldClassroom(Classroom oldClassroom) {
        this.oldClassroom = oldClassroom;
    }

    public Classroom getNewClassroom() {
        return newClassroom;
    }

    public void setNewClassroom(Classroom newClassroom) {
        this.newClassroom = newClassroom;
    }

    public ArrayList<Classroom> getOtherEqualClassrooms() {
        return otherEqualClassrooms;
    }

    public void setOtherEqualClassrooms(ArrayList<Classroom> otherEqualClassrooms) {
        this.otherEqualClassrooms = otherEqualClassrooms;
    }

    public ArrayList<Classroom> getOtherWorseClassrooms() {
        return otherWorseClassrooms;
    }

    public void setOtherWorseClassrooms(ArrayList<Classroom> otherWorseClassrooms) {
        this.otherWorseClassrooms = otherWorseClassrooms;
    }

    public String toString() {
        // Print error if anything is null
        if (course == null || courseTitle == null || oldClassroom == null || newClassroom == null) {
            return "Rescheduling could not be completed. The course may be unable to move from it's current classroom, or no classroom is big enough to accomodate the increased size. Please select a different course.";
        }
        String equalString = "";
        String worseString = "";
        // Create the list of equal options
        if (otherEqualClassrooms.size() == 0) {
            equalString = "None. ";
        } else {
            for (Classroom room : otherEqualClassrooms) {
                equalString += "PKI " + room.getRoom() + ", ";
            }
        }
        equalString = equalString.substring(0, equalString.length() - 2) + ".";
        // Create the list of worse options
        if (otherWorseClassrooms.size() == 0) {
            worseString = "None. ";
        } else {
            for (Classroom room : otherWorseClassrooms) {
                worseString += "PKI " + room.getRoom() + ", ";
            }
        }
        worseString = worseString.substring(0, worseString.length() - 2) + ".";
        // Return full String
        return course + " " + courseTitle + " Section " + sectionNumber + " can be moved from PKI " + oldClassroom.getRoom() + " to PKI " + newClassroom.getRoom() + ". \n\n" +
        "Other equally suitable options are: " + equalString + "\n\n" +
        "Other less suitable but available options are: " + worseString;
    }
}
