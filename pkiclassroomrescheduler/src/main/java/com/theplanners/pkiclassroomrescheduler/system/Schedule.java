package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class Schedule {
    private ArrayList<Course> schedule;

    public Schedule(){
        this.schedule = new ArrayList<Course>();
    }

    public void addCourse(Course course){
        this.schedule.add(course);
    }

    public ArrayList<Course> returnSchedule(){
        return this.schedule;
    }

    public void clearSchedule(){
        this.schedule.clear();
    }
}
