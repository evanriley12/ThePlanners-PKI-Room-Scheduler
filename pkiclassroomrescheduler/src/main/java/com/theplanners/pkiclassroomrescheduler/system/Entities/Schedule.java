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

    public ArrayList<Section> returnCSSchedule(){
        ArrayList<Section> csSchedule = new ArrayList<Section>();
        for(Section section : schedule)
        {
            String course = section.getCourse();
            if(course.contains("BIOI") || 
            course.contains("BMI") || 
            course.contains("CIST") || 
            course.contains("CNST") || 
            course.contains("CSCI") || 
            course.contains("CYBR") || 
            course.contains("ISQA") || 
            course.contains("ITIN") || 
            course.contains("MATH") || 
            course.contains("SCMT")) {
                csSchedule.add(section);
            }
        }
        return csSchedule;
    }

    /**
     * Empties the list of sections in the schedule.
     */
    public void clearSchedule(){
        this.schedule.clear();
    }

    public void updateCrosslist()
    {
        for(Section section : schedule){
            section.setCrossListMax(schedule);
        }
    }

    public void updateSchedule(Section sectionToUpdate, int newRoom) {
        // Find the section in the schedule
        for (Section section : this.schedule) {
            if (section == sectionToUpdate) {
                section.setRoomNumber(newRoom);
            }
        }
    }
}
