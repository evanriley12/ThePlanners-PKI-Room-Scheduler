package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theplanners.pkiclassroomrescheduler.system.Entities.Classroom;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;

/**
* A controller that hosts an endpoint that returns the list of classrooms
* @see com.theplanners.pkiclassroomrescheduler.system.Entities.Classroom
*/
@RestController
@RequestMapping("/api")
public class ClassroomInfoController {
    /**
    * The Autowired ClassroomList instance used for managing classroom information
    */
    private final ClassroomList classroomList;

    /**
    * Constructs a new ClassroomInfoController with the provided Schedule and ClassroomList instances.
    * Uses injection to share across components
    * 
    * @param classroomList The ClassroomList instance to use for managing classroom information.
    */
    public ClassroomInfoController(ClassroomList classroomList){
        this.classroomList = classroomList;
    }

    /**
    * Returns the list of classrooms at endpoint /api/class
    * @return the list of classrooms in PKI
    */
    @GetMapping("/class")
    public ArrayList<Classroom> returnClassroomList(){
        return classroomList.returnClassrooms();
    }
}
