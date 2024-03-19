package com.theplanners.pkiclassroomrescheduler.system;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList; 

public class Section {
    private String term;
    private String departmentCode;
    private String subjectCode;
    private int catalogNumber;
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

    public Section(String term, String departmentCode, String subjectCode, int catalogNumber, 
        String course, int sectionNumber, String courseTitle, String sectionType, 
        ArrayList<DayOfWeek> meetingDays, LocalTime startTime, LocalTime endTime, String instructor, 
        int roomNumber, String instructionMethod, String crossList){
            this.term = term;
            this.departmentCode = departmentCode;
            this.subjectCode = subjectCode;
            this.catalogNumber = catalogNumber;
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
        }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
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

    @Override
    public String toString() {
        return "Section [term=" + term + ", departmentCode=" + departmentCode + ", subjectCode=" + subjectCode
                + ", catalogNumber=" + catalogNumber + ", course=" + course + ", sectionNumber=" + sectionNumber
                + ", courseTitle=" + courseTitle + ", sectionType=" + sectionType + ", meetingDays=" + meetingDays
                + ", startTime=" + startTime + ", endTime=" + endTime + ", instructor=" + instructor + ", roomNumber="
                + roomNumber + ", instructionMethod=" + instructionMethod + ", crossList=" + crossList + "]";
    }
}
