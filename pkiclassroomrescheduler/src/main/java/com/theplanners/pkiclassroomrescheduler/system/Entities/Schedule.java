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
     * Retrieves the computer science related courses from the schedule.
     * @return An ArrayList of every Computer Science course in the schedule.
     */
    public ArrayList<Section> returnCSSchedule(){
        ArrayList<Section> csSchedule = new ArrayList<Section>();
        for(Section section : schedule)
        {
            String course = section.getCourse();
            if(course.contains("BIOI") || 
            course.contains("BMI") || 
            course.contains("CIST") || 
            course.contains("CSCI") || 
            course.contains("CYBR") || 
            course.contains("ISQA") || 
            course.contains("ITIN") || 
            course.contains("MATH")) {
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

    /**
     * Updates the crosslisting information in the schedule.
     */
    public void updateCrosslist()
    {
        for(Section section : schedule){
            section.setCrossListMax(schedule);
        }
    }

    /**
     * Updates a section in the schedule with a new classroom assignment.
     * @param sectionToUpdate The section that will be updated.
     * @param newRoom The new room number the section will be assigned to.
     */
    public void updateSchedule(Section sectionToUpdate, int newRoom) {
        // Find the section in the schedule
        for (Section section : this.schedule) {
            if (section == sectionToUpdate) {
                section.setRoomNumber(newRoom);
            }
        }
    }
}
