package com.theplanners.pkiclassroomrescheduler.system;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private ArrayList<Section> overlappingSections = new ArrayList<Section>();

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public ArrayList<DayOfWeek> getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(ArrayList<DayOfWeek> meetingDays) {
        this.meetingDays = meetingDays;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getInstructionMethod() {
        return instructionMethod;
    }

    public void setInstructionMethod(String instructionMethod) {
        this.instructionMethod = instructionMethod;
    }

    public String getCrossList() {
        return crossList;
    }

    public void setCrossList(String crossList) {
        this.crossList = crossList;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public boolean Overlaps(Section otherSection) {
        ArrayList<DayOfWeek> copy = meetingDays;
        copy.retainAll(otherSection.getMeetingDays());
        if ((this.startTime.isAfter(otherSection.startTime) || this.startTime.equals(otherSection.startTime)) &&
            (this.startTime.isBefore(otherSection.endTime) || this.startTime.equals(otherSection.endTime)) &&
            (copy.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Section> getOverlappingSections() {
        return overlappingSections;
    }

    public void addOverlappingSection(Section section){
        overlappingSections.add(section);
    }

    public void setOverlappingSections(ArrayList<Section> overlappingSections) {
        this.overlappingSections = overlappingSections;
    }

    @Override
    public String toString() {
        return "Section [course=" + course + ", sectionNumber=" + sectionNumber + ", courseTitle=" + courseTitle
                + ", sectionType=" + sectionType + ", meetingDays=" + meetingDays + ", startTime=" + startTime
                + ", endTime=" + endTime + ", instructor=" + instructor + ", roomNumber=" + roomNumber
                + ", instructionMethod=" + instructionMethod + ", crossList=" + crossList + ", enrollment=" + enrollment
                + ", maxEnrollment=" + maxEnrollment + ", overlappingSections=" + overlappingSections + "]";
    }


}
