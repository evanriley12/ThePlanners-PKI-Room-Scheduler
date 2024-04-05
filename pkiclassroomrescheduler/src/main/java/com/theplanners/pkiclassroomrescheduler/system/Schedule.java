package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class Schedule {
    private ArrayList<Section> schedule;

    public Schedule(){
        this.schedule = new ArrayList<Section>();
    }

    public void addSection(Section section){
        this.schedule.add(section);
    }

    public ArrayList<Section> returnSchedule(){
        return this.schedule;
    }

    public void clearSchedule(){
        this.schedule.clear();
    }
}
