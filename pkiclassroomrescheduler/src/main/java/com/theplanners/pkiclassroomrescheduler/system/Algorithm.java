package com.theplanners.pkiclassroomrescheduler.system;
import java.util.ArrayList;

public class Algorithm {

    private final Schedule schedule;
    private final ClassroomList classroomList;

    public Algorithm(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    public String doAlgorithm(Section section, int newSize) {
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
}