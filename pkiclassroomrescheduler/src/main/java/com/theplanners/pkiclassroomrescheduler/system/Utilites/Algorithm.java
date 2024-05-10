package com.theplanners.pkiclassroomrescheduler.system.Utilites;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.theplanners.pkiclassroomrescheduler.system.Entities.Classroom;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Result;

@Service
/**
 * Algorithm is a class that takes a created schedule, which contains information for all courses,
 * and performs analysis on each course to create relationships between them and determine which classrooms
 * can support a given change to the schedule.
 */
public final class Algorithm {
    /**
     * Takes a schedule and adds overlaps to each section within the schedule if the two courses time's overlap.
     * @param schedule The schedule object, which is a collection of all the currently scheduled courses.
     */
    public static void updateOverlaps(Schedule schedule) {
        // Get all sections in the schedule.
        ArrayList<Section> allSections = schedule.returnSchedule();
        // For each section, loop through every other section to determine if there is an overlap
        for (Section section : allSections) {
            for (Section section2 : allSections) {
                // If there is an overlap and the sections arent the same section, create an edge.
                if (!section.equals(section2) && section.Overlaps(section2)) {
                    section.addOverlappingSection(section2);
                    //System.out.println("Added " + section2.getCourseTitle() + " to " + section.getCourseTitle() + "'s overlaps.");
                }
            }
            // System.out.println(section.getCourseTitle() + " overlaps with classes in rooms:");
            // for (Section sec : section.getOverlappingSections()) {
            //     System.out.println(sec.getRoomNumber());
            // }
        }
    }
    
    /**
     * Our algorithm that analyzes the overlaps between each course in the schedule to determine the best option for rescheduling 
     * a given course.
     * @param section The Section object within the schedule that needs to be rescheduled.
     * @param newSize An integer containign the updated course size that a classroom needs to accomodate.
     * @param schedule The Schedule object which is a collection of all sections in the schedule.
     * @param classroomList A ClassroomList object containing each Classroom object available in the building.
     * @return An array list containing Result objects that contain all 
     */
    public static ArrayList<Result> doAlgorithm(Section section, int newSize, Schedule schedule, ClassroomList classroomList) {
        // Create the graph and get a reference to the section in the schedule
        updateOverlaps(schedule);
        // Create an array list to store all results
        ArrayList<Result> results = new ArrayList<Result>();
        // Get all classrooms
        ArrayList<Classroom> allClassrooms = classroomList.returnClassrooms();
        // Get all adjacent sections
        ArrayList<Section> neighbors = section.getOverlappingSections();
        // Filter out classrooms that will not work
        ArrayList<Classroom> possibleClassrooms = new ArrayList<Classroom>();
        // Iterate through every classroom save the current classroom, and the the biggest classroom
        int biggestRoom = 0;
        Classroom oldClassroom = null;
        for (int i = 0; i < allClassrooms.size(); i++) {
            // Check if the classroom being checked is the sections current class or if it is too small to accomodate the new size. 
            // If either is true, move on, otherwise, add the classroom to the list of potential classrooms.
            if (section.getRoomNumber() == allClassrooms.get(i).getRoom()) {
                // Store the old classroom as a Classroom object rather than a number
                oldClassroom = allClassrooms.get(i);
            } else if (allClassrooms.get(i).getSeats() < newSize) {
                continue;
            } else {
                possibleClassrooms.add(allClassrooms.get(i));
            }
            // Update the biggest room (it should always be 100 but in the event of future changes it will handle it)
            if (allClassrooms.get(i).getSeats() > biggestRoom) {
                biggestRoom = allClassrooms.get(i).getSeats();
            }
        }
        // Return null if the previous classroom is null (shouldn't happen anymore) or if the newSize is bigger than the biggest room in PKI.
        if (oldClassroom == null || biggestRoom < newSize) {
            return null;
        }
        // Now with a list of potential rooms, see if any of the rooms are not in use by an adjacent node.
        for (int i = 0; i < possibleClassrooms.size(); i++) {
            for (Section neighbor : neighbors) {
                // If a neighbor already has one of the possible classrooms, remove said classroom from the list of possibilities.
                if (neighbor.getRoomNumber() == possibleClassrooms.get(i).getRoom()) {
                    try {
                        possibleClassrooms.remove(i);
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        // Now the list of rooms only contains rooms that can actually be used.
        // Check if there are no suitable rooms
        if (possibleClassrooms.isEmpty()) {
            // Determine which neighbors could work
            ArrayList<Section> viableNeighbors = new ArrayList<Section>();
            for (Section neighbor : neighbors) {
                if (classroomList.getClassroomByNumber(neighbor.getRoomNumber()).getSeats() >= newSize) {
                    viableNeighbors.add(neighbor);
                }
            }
            // Now we have a list of each neighbor that can fit the newSize
            // Check to see if there are any viable neighbors
            if (viableNeighbors.isEmpty()) {
                return null;
            }
            // Iterate through each potential neighbor to find a suitable classroom
            for (Section neighbor : viableNeighbors) {
                // Get the neighbor's classroom
                Classroom neighborClassroom = classroomList.getClassroomByNumber(neighbor.getRoomNumber());
                // Attempt to reschedule the current neighbor
                ArrayList<Result> recursiveResults = doAlgorithm(neighbor, neighbor.getCrossListMax(), schedule, classroomList);
                // Check if scheduling was successful
                if (recursiveResults != null && !recursiveResults.isEmpty()) {
                    // Rescheduling was successful
                    results.add(new Result(section, oldClassroom, neighborClassroom, new ArrayList<Classroom>(), new ArrayList<Classroom>(), newSize));
                    schedule.updateSchedule(section, neighborClassroom.getRoom());
                    results.addAll(recursiveResults);
                    return results;
                } else {
                    // Rescheduling was unsuccessful, move on to the next neighbor
                    continue;
                }
            }
            return null;
        } else {
            // Determine which of the remaining classes is the best option.
            Classroom bestClassroom = possibleClassrooms.get(0);
            for (int i = 1; i < possibleClassrooms.size(); i++) {
                if (possibleClassrooms.get(i).getSeats() < bestClassroom.getSeats()) {
                    bestClassroom = possibleClassrooms.get(i);
                }
            }
            // Determine if the other options are just as good or worse than the best.
            ArrayList<Classroom> otherBest = new ArrayList<Classroom>();
            ArrayList<Classroom> otherWorst = new ArrayList<Classroom>();
            for (int i = 0; i < possibleClassrooms.size(); i++) {
                if (possibleClassrooms.get(i).getSeats() == bestClassroom.getSeats() && possibleClassrooms.get(i) != bestClassroom) {
                    otherBest.add(possibleClassrooms.get(i));
                } else if (possibleClassrooms.get(i) != bestClassroom) {
                    otherWorst.add(possibleClassrooms.get(i));
                }
            }
            // Get the result
            results.add(new Result(section, oldClassroom, bestClassroom, otherBest, otherWorst, newSize));
            //Update the schedule
            schedule.updateSchedule(section, bestClassroom.getRoom());
            for (Result result : results) {
                System.out.println(result.toString());
            }
            return results;
        }
    }
}