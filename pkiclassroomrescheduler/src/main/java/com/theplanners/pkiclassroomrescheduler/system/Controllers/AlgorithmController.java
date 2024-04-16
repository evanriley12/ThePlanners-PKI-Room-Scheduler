package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.Algorithm;

@RestController
@RequestMapping("/api")
public class AlgorithmController {

    /**
    * The Autowired Schedule instance used for managing course assignments
    */
    private final Schedule schedule;
    /**
    * The Autowired ClassroomList instance used for managing classroom information
    */
    private final ClassroomList classroomList;

    /**
    * Constructs a new AlgorithmController with the provided Schedule and ClassroomList instances.
    * Uses injection to share across components
    *
    * @param schedule The Schedule instance to use for managing course assignments.
    * @param classroomList The ClassroomList instance to use for managing classroom information.
    */
    public AlgorithmController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

    /**
     * Runs the reassignment algorithm for a given section with given new max enrollment size. 
     * 
     * @param classSection section name "AREN 3030 - Section 1"
     * @param newSize new max enrollment size 
     * @return the optimal rearrangement of classrooms if one exists
     */
    @GetMapping("/algorithm")
    public String runAlgorithm(@RequestParam String classSection, @RequestParam int newSize){

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

        return Algorithm.doAlgorithm(result, newSize, schedule, classroomList).toString();
    }
}
