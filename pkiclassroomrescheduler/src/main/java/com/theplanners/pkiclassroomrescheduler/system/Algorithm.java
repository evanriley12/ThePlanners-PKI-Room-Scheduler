package com.theplanners.pkiclassroomrescheduler.system;
import java.util.ArrayList;

public class Algorithm {

    private final Schedule schedule;
    private final ClassroomList classroomList;

    public Algorithm(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    public void updateOverlaps(){
        ArrayList<Section> allSection = schedule.returnSchedule();
        for(Section section : allSection)
        {
            for(Section section2 : allSection)
            {
                //System.out.println(section.getCourse() + " " + section.getSectionNumber());
                //System.out.println(section2.getCourse() + " " + section2.getSectionNumber());
                if(section.Overlaps(section2)){
                    section.addOverlappingSection(section2);
                }
            }
            System.out.println(section.getOverlappingSections().size());
        }
    }

    public String doAlgorithm(Section section, int newSize) {
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

    public String doAlgorithmNew(Section section, int newSize) {
        // Get all classrooms
        ArrayList<Classroom> allClassrooms = classroomList.returnClassrooms();
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
            // TODO: Get adjacent classes from the schedule and check classrooms. 
        }
        return "";
    }
}