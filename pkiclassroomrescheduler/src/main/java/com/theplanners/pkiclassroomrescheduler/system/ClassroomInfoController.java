package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClassroomInfoController {
    ClassroomList classroomList;

    public ClassroomInfoController(ClassroomList classroomList){
        this.classroomList = classroomList;
    }

    @GetMapping("/class")
    public ArrayList<Classroom> returnClassroomList(){
        return classroomList.returnClassrooms();
    }
}
