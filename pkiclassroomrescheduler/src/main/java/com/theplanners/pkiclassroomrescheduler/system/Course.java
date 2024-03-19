package com.theplanners.pkiclassroomrescheduler.system;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Section> sections;

    public Course(String courseName){
        this.courseName = courseName;
        this.sections = new ArrayList<Section>();
    }

    public void setName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public void addSection(Section section){
        this.sections.add(section); 
    }

    public ArrayList<Section> getSections(){
        return this.sections;
    }
}
