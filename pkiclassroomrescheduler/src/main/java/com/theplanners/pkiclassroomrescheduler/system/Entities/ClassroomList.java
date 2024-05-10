package com.theplanners.pkiclassroomrescheduler.system.Entities;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
/**
 * ClassroomList is a class that serves as an object representing a list of 
 * classrooms along with all of the actions that are able to be taken to with
 * the list of classrooms.
 */
public class ClassroomList {
    private ArrayList<Classroom> classrooms;

    /**
     * The constructor for the ClassroomList class. The constructor
     * stores a list of classroom objects into an ArrayList.
     */
    public ClassroomList() {
        this.classrooms = new ArrayList<Classroom>();
    }

    /**
     * Adds a classroom to the ArrayList of classrooms that
     * is intitialized within the class constructor.
     * @param classroom the classroom to be added to the classroom list
     */
    public void addClassroom(Classroom classroom){
        this.classrooms.add(classroom);
    }

    /**
     * Returns the ArrayList containing lassroom objects.
     * @return the ArrayList of classrooms to be returned.
     */
    public ArrayList<Classroom> returnClassrooms(){
        return this.classrooms;
    }

    /**
     * Sets the list of classrooms provided an input ArrayList of classrooms.
     * @param newClassrooms the ArrayList of new classrooms
     */
    public void setClassrooms(ArrayList<Classroom> newClassrooms){
        classrooms = newClassrooms;
    }

    /**
     *  Empties the list of classrooms.
     */
    public void clearClassrooms(){
        this.classrooms.clear();
    }

    /**
     * Finds a classroom based on the room number.
     * @param roomNumber An integer of the room number to be found.
     * @return A classroom object that matches the number input.
     */
    public Classroom getClassroomByNumber(int roomNumber) {
        for (Classroom classroom : classrooms) {
            if (classroom.getRoom() == roomNumber) {
                return classroom;
            }
        }
        return null;
    }
}
