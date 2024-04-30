package com.theplanners.pkiclassroomrescheduler.system.Entities;

import java.util.ArrayList;

/**
 * The Result object stores all relevant information about the result of the algorithm for easy access.
 */
public class Result {
    private String course;
    private String courseTitle;
    private int sectionNumber;
    private Classroom oldClassroom;
    private Classroom newClassroom;
    private ArrayList<Classroom> otherEqualClassrooms;
    private ArrayList<Classroom> otherWorseClassrooms;
    private int newSize;

    /**
     * The constructor for a Result object
     * @param section The section of the course that was rescheduled.
     * @param oldClassroom The section's classroom assignment before the algorithm executed.
     * @param newClassroom The section's new classroom assignment after the algorithm executed.
     * @param otherEqualClassrooms Other available classrooms of equal efficiency to the newClassroom.
     * @param otherWorseClassrooms Other available classrooms of worse efficiency than the newClassroom.
     * @param newSize The new size that the section can accomodate.
     */
    public Result(Section section, Classroom oldClassroom, Classroom newClassroom, ArrayList<Classroom> otherEqualClassrooms, ArrayList<Classroom> otherWorseClassrooms, int newSize) {
        this.course = section.getCourse();
        this.courseTitle = section.getCourseTitle();
        this.sectionNumber = section.getSectionNumber();
        this.oldClassroom = oldClassroom;
        this.newClassroom = newClassroom;
        this.otherEqualClassrooms = otherEqualClassrooms;
        this.otherWorseClassrooms = otherWorseClassrooms;
        this.newSize = newSize;
    }

    /**
     * Formats the result's information into a readable string.
     * @return A string containing all of the result's information.
     */
    public String toString() {
        // Print error if anything is null
        if (course == null || courseTitle == null || oldClassroom == null || newClassroom == null) {
            return "Rescheduling could not be completed. No classroom is big enough to accomodate the increased size, so a new section will need to be created.";
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
        return course + " " + courseTitle + " Section " + sectionNumber + " can be moved from PKI " + oldClassroom.getRoom() + " to PKI " + newClassroom.getRoom() + " to accomodate " + newSize + " students.\n\n" +
        "Other equally suitable options are: " + equalString + "\n\n" +
        "Other less suitable but available options are: " + worseString;
    }
}
