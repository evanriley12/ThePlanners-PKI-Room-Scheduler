package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ClassroomList {
    private ArrayList<Classroom> classrooms;

    public ClassroomList() {
        this.classrooms = new ArrayList<Classroom>();
    }

    public void addClassroom(Classroom classroom){
        this.classrooms.add(classroom);
    }

    public ArrayList<Classroom> returnClassrooms(){
        return this.classrooms;
    }

    public void clearClassrooms(){
        this.classrooms.clear();
    }
}
