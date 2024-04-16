package com.theplanners.pkiclassroomrescheduler.system.Entities;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
/**
 * A Schedule is an object that contins all of the sections taking place in PKI.
 */
public class Schedule {
    private ArrayList<Section> schedule;

    public Schedule(){
        this.schedule = new ArrayList<Section>();
    }

    /**
     * Adds a section to the ArrayList of sections.
     * @param section The Section object to be added to the schedule.
     */
    public void addSection(Section section){
        this.schedule.add(section);
    }

    /**
     * Retrieves the entire schedule.
     * @return An ArrayList of every class in the schedule.
     */
    public ArrayList<Section> returnSchedule(){
        return this.schedule;
    }

    /**
     * Empties the list of sections in the schedule.
     */
    public void clearSchedule(){
        this.schedule.clear();
    }
}