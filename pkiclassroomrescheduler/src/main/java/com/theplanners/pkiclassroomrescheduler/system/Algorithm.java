package com.theplanners.pkiclassroomrescheduler.system;
import java.util.ArrayList;

public class Algorithm {

    private final Schedule schedule;
    private final ClassroomList classroomList;

    public Algorithm(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    public void updateOverlaps() {
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
        }
    }

    public String doAlgorithmOld(Section section, int newSize) {
        updateOverlaps();
        int currentRoom = section.getRoomNumber();
        ArrayList<Classroom> classrooms = classroomList.returnClassrooms();
        ArrayList<String> newRooms = new ArrayList<String>();
        for (int i = 0; i < classrooms.size(); i++) {
            if (classrooms.get(i).getRoom() != currentRoom && classrooms.get(i).getSeats() >= newSize) {
                newRooms.add(Integer.toString(classrooms.get(i).getRoom()));
            }
        }
        return newRooms.toString();
    }

    public String doAlgorithm(Section section, int newSize) {
        // Create the graph
        updateOverlaps();
        // Get all classrooms
        ArrayList<Classroom> allClassrooms = classroomList.returnClassrooms();
        // Get all adjacent sections
        ArrayList<Section> neighbors = section.getOverlappingSections();
        // Filter out classrooms that will not work
        ArrayList<Classroom> possibleClassrooms = new ArrayList<Classroom>();
        // Iterate through every classroom
        for (int i = 0; i < allClassrooms.size(); i++) {
            // Check if the classroom being checked is the sections current class or if it is too small to accomodate the new size. 
            // If either is true, move on, otherwise, add the classroom to the list of potential classrooms.
            if (section.getRoomNumber() == allClassrooms.get(i).getRoom() || allClassrooms.get(i).getSeats() < newSize) {
                continue;
            } else {
                possibleClassrooms.add(allClassrooms.get(i));
            }
        }
        // Check if the list of possible classrooms is empty before continuing.
        if (possibleClassrooms.size() <= 0) {
            return null;
        }
        // Now with a list of potential rooms, see if any of the rooms are not in use by an adjacent node.
        for (int i = 0; i < possibleClassrooms.size(); i++) {
            for (Section neighbor : neighbors) {
                // If a neighbor already has one of the possible classrooms, remove said classroom from the list of possibilities.
                if (neighbor.getRoomNumber() == possibleClassrooms.get(i).getRoom()) {
                    possibleClassrooms.remove(i);
                }
            }
        }
        // Now the list of rooms only contains rooms that can actually be used.
        // Check if there are no suitable rooms
        if (possibleClassrooms.size() == 0) {
            // TODO: Recursive case.
            return "No available classrooms.";
        } else {
            // Determine which of the remaining classes is the best option.
            Classroom bestClassroom = possibleClassrooms.get(0);
            for (int i = 1; i < possibleClassrooms.size(); i++) {
                if (possibleClassrooms.get(i).getSeats() < bestClassroom.getSeats()) {
                    bestClassroom = possibleClassrooms.get(i);
                }
            }
            String bestOption = "The best option is to move " + section.getCourse() + " Section: " + section.getSectionNumber() + " to room " + bestClassroom.getRoom() + ".";
            // Determine if the other options are just as good or worse than the best.
            String otherBest = "Other options with the same class size: None";
            String otherWorst ="Other options with larger class size: None";
            for (int i = 0; i < possibleClassrooms.size(); i++) {
                if (possibleClassrooms.get(i).getSeats() == bestClassroom.getSeats() && possibleClassrooms.get(i) != bestClassroom) {
                    if (otherBest.length() == 44) {
                        otherBest = otherBest.substring(0, otherBest.length() - 4);
                    }
                    otherBest += possibleClassrooms.get(i).getRoom() + ", ";
                } else if (possibleClassrooms.get(i) != bestClassroom) {
                    if (otherWorst.length() == 42) {
                        otherWorst = otherWorst.substring(0, otherWorst.length() - 4);
                    }
                    otherWorst += possibleClassrooms.get(i).getRoom() + ", ";
                }
            }
            // Chop off the last two characters to remove the commas from the end.
            otherBest = otherBest.substring(0, otherBest.length() - 2);
            otherWorst = otherWorst.substring(0, otherWorst.length() - 2);
            // Return the results.
            String results = bestOption + "\n\n" + otherBest + "\n\n" + otherWorst;
            return results;
        }
    }
}