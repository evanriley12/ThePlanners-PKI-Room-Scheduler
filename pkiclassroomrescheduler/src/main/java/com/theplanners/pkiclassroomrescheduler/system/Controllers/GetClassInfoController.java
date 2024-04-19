package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;

/**
 * A controller hosting endpoints /api to return information about sections uploaded
 * from a csv
 */
@RestController
@RequestMapping("/api")
public class GetClassInfoController {

    /**
    * The Autowired Schedule instance used for managing course assignments
    */
    private final Schedule schedule;
    /**
    * The Autowired ClassroomList instance used for managing classroom information
    */
    private final ClassroomList classroomList;

    /**
     * Constructs a new FileUploadController with the provided Schedule and ClassroomList instances.
     * Uses injection to share across components
     * 
     * @param schedule The Schedule instance to use for managing course assignments.
     * @param classroomList The ClassroomList instance to use for managing classroom information.
     */
    public GetClassInfoController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    /**
     * Endpoint at /api/classInfo that takes in a section name as a paramater to return info on the section
     * @param classSection String that is the name of a class section
     * @return returns the requested section
     */
    @GetMapping("/classInfo")
    public Section getClassInfo(@RequestParam String classSection) {
        String[] courseSection = classSection.split("-");
        for(int i = 0 ; i < courseSection.length ; i++){
            courseSection[i] = courseSection[i].trim();
        }
        Section result = null;
        for(Section section : schedule.returnSchedule()){
            if(section.getCourse().equals(courseSection[0]) && section.getSectionNumber() == Integer.parseInt(courseSection[1].replaceAll("[^\\d.]", ""))){
                result = section;
                break;
            }
        }
        return result;
    }

    /**
     * Endpoint at /api/all that returns the entire schedule
     * @return the entire schedule
     */
    @GetMapping("/all")
    @JsonManagedReference
    public ArrayList<Section> getMethodName() {
        return schedule.returnSchedule();
    }
    

}



