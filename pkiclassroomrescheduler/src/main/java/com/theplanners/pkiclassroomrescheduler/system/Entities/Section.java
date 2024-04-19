package com.theplanners.pkiclassroomrescheduler.system.Entities;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * A Section is an object that contains all information about a class in the schedule.
 * It is populated with information based on what is parsed from the uploaded csv.
 */
public class Section {
    private String course;
    private int sectionNumber;
    private String courseTitle;
    private String sectionType;
    private ArrayList<DayOfWeek> meetingDays;
    private LocalTime startTime;
    private LocalTime endTime;
    private String instructor;
    private int roomNumber;
    private String instructionMethod;
    private String crossList;
    private int enrollment;
    private int maxEnrollment;
    private int crossListMax;
    private ArrayList<String> crossListedSections = new ArrayList<String>();
    @JsonManagedReference
    private ArrayList<Section> overlappingSections = new ArrayList<Section>();

    /**
     * The constructor for a Section object, where each piece of relevant data can be added.
     * @param course A string containing the course abbreviation and number Ex. CSCI 4800.
     * @param sectionNumber An integer containing the section number, for cases where there are multiple instances of the saem course.
     * @param courseTitle A string containing the actual title of a course Ex. Advanced Java Programming.
     * @param sectionType A string containing the type of course it is, such as a lab or lecture.
     * @param meetingDays A ArrayList containing each of the days of the week that the couse takes place on.
     * @param startTime A LocalTime object indicating the start time of the course.
     * @param endTime A LocalTime onject indicating the end time of the course.
     * @param instructor A string containing the name of the teacher for the course.
     * @param roomNumber An integer containing the room number in PKI that the class is currently taking place in.
     * @param instructionMethod A string indicating whether the course is taught in person, online, or hybrid.
     * @param crossList A String indicating any sections that the course is crosslisted with.
     * @param enrollment An integer indicating the current number of students enrolled in the course.
     * @param maxEnrollment An integer indicating the maximum number of students allowed in the course.
     */
    public Section(
        String course, int sectionNumber, String courseTitle, String sectionType, 
        ArrayList<DayOfWeek> meetingDays, LocalTime startTime, LocalTime endTime, String instructor, 
        int roomNumber, String instructionMethod, String crossList, int enrollment, int maxEnrollment){
            this.course = course;
            this.sectionNumber = sectionNumber;
            this.courseTitle = courseTitle;
            this.sectionType = sectionType;
            this.meetingDays = meetingDays;
            this.startTime = startTime;
            this.endTime = endTime;
            this.instructor = instructor;
            this.roomNumber = roomNumber;
            this.instructionMethod = instructionMethod;
            this.crossList = crossList;
            this.enrollment = enrollment;
            this.maxEnrollment = maxEnrollment;
        }
    /**
     * Retrives the course abbreviation from the Section.
     * @return A string containing the course abbreviation.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course abbreviation for the Section.
     * @param course A string containing the new course abbreviation.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Retrieves the course's section number.
     * @return An integer containing the course's section number.
     */
    public int getSectionNumber() {
        return sectionNumber;
    }
    
    /**
     * Sets the course's section number.
     * @param sectionNumber An integer containing the new section number.
     */
    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    /**
     * Retrieves the course's title.
     * @return A string containing the course's title.
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the course's title.
     * @param courseTitle A string containing the course's new title.
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * Retrieves the course's section type.
     * @return A string containing the course's section type.
     */
    public String getSectionType() {
        return sectionType;
    }
    /**
     * Sets the course's section type.
     * @param sectionType A string containing the course's new section type.
     */
    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    /**
     * Retrieves the meeting days of the course.
     * @return An ArrayList of the days of the week the course meets.
     */
    public ArrayList<DayOfWeek> getMeetingDays() {
        return meetingDays;
    }

    /**
     * Sets the course's meeting days.
     * @param meetingDays An ArrayList containing the new days of the week the course meets.
     */
    public void setMeetingDays(ArrayList<DayOfWeek> meetingDays) {
        this.meetingDays = meetingDays;
    }

    /**
     * Retrieves the start time of the course.
     * @return A LocalTime object containing the course's starting time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the course's start time.
     * @param startTime A LocalTime object containing the course's new start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the ending time of the course.
     * @return A LocalTime object containing the course's ending time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
    * Sets the course's ending time.
    * @param startTime A LocalTime object containing the course's new ending time
    */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the teacher of the course.
     * @return A string containting the course's instructor.
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the course's instructor.
     * @param instructor A string containing the course's new instructor.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Retrieves the room number of the course.
     * @return An integer containing the room number of course's classroom in PKI.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number of the course.
     * @param roomNumber An integer containing the new room number of the course's classroom in PKI.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Retrieves the intruction method of the course.
     * @return A string containing the course's new instruction method.
     */
    public String getInstructionMethod() {
        return instructionMethod;
    }

    /**
     * Sets the instruction method of the course.
     * @param instructionMethod A string containing the course's new instruction method.
     */
    public void setInstructionMethod(String instructionMethod) {
        this.instructionMethod = instructionMethod;
    }

    /**
     * Retrieves the name of the courses crosslisted with the course.
     * @return A string containing the names of crosslisted courses.
     */
    public String getCrossList() {
        return crossList;
    }

    /**
     * Sets the cross listed courses associated with the course.
     * @param crossList A String containing the new courses that are crosslisted.
     */
    public void setCrossList(String crossList) {
        this.crossList = crossList;
    }

    /**
     * Retrieves the enrollment number of the course.
     * @return An integer containing the number of students enrolled in the course.
     */
    public int getEnrollment() {
        return enrollment;
    }

    /**
     * Sets the enrollment number of the course.
     * @param enrollment An integer containing the new number of student enrolled in the course.
     */
    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    /**
     * Retrieves the maximum enrollment of the course.
     * @return An integer containing the maximum enrollment of the course.
     */
    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    /**
     * Sets the maximum enrollment number of the course.
     * @param maxEnrollment An integer containing the new maximum enrollment number.
     */
    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    /**
     * Indicates if the current section overlaps in time with another given section.
     * @param otherSection The Section to be compared to this section.
     * @return A boolean indicating if the two courses overlap.
     */
    public boolean Overlaps(Section otherSection) {
        // Create a copy of the meeting days.
        ArrayList<DayOfWeek> copy = new ArrayList<>(meetingDays);
        // Get the intersection of the two section's days.
        copy.retainAll(otherSection.getMeetingDays());
        // If days do not overlap, return false.
        if (copy.isEmpty()) {
            return false;
        }
        // Check the overlapping conditions
        boolean condition1 = !this.startTime.isAfter(otherSection.endTime);
        boolean condition2 = !this.endTime.isBefore(otherSection.startTime);
        // Return the result
        return condition1 && condition2;
    }

    /**
     * Retrieves the sections that the course overlaps with.
     * @return An ArrayList containing Section objects that the section overlaps with.
     */
    public ArrayList<Section> getOverlappingSections() {
        return overlappingSections;
    }

    /**
     * Adds an additional overlapping section to the section's list of overlapping sections.
     * @param section The section to be added to the list of overlapping sections.
     */
    public void addOverlappingSection(Section section){
        overlappingSections.add(section);
    }

    /**
     * Set the section's overlapping sections.
     * @param overlappingSections An ArrayList containing the section's new overlapping sections.
     */
    public void setOverlappingSections(ArrayList<Section> overlappingSections) {
        this.overlappingSections = overlappingSections;
    }

    public void setCrossListMax(ArrayList<Section> schedule) {
        crossListMax = 0;
        for(Section section : schedule){
            if (crossListedSections.contains(section.getCourse() + "-00" + Integer.toString(section.getSectionNumber()))){
                crossListMax += section.maxEnrollment;
            }
        }
        crossListMax += maxEnrollment;
    }

    /**
     * Return the max course size considering cross listings
     * @return max course size including crosslisted courses.
     */
    public int getCrossListMax() {
        return crossListMax;
    }

    /**
     * Add a single course to the cross listed courses.
     * @param crosslistedSection a section that is crosslisted with this section
     */
    public void addCrossList(String crosslistedSection){
        crossListedSections.add(crosslistedSection);
    }

    /**
     * Set the entire crosslisted sections
     * @param allCrossListed an arraylist of all crosslisted sections
     */
    public void setCrossListed(ArrayList<String> allCrossListed) {
        crossListedSections = allCrossListed;
    }

    @Override
    /**
     * Formats the section's information into a readable string.
     * @return A string containing all the section's information.
     */
    public String toString() {
        return "Section [course=" + course + ", sectionNumber=" + sectionNumber + ", courseTitle=" + courseTitle
                + ", sectionType=" + sectionType + ", meetingDays=" + meetingDays + ", startTime=" + startTime
                + ", endTime=" + endTime + ", instructor=" + instructor + ", roomNumber=" + roomNumber
                + ", instructionMethod=" + instructionMethod + ", crossList=" + crossList + ", enrollment=" + enrollment
                + ", maxEnrollment=" + maxEnrollment;
    }


}
