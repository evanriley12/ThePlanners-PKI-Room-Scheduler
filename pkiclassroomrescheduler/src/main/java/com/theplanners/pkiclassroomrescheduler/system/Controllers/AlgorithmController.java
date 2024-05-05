package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Result;
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
    public ArrayList<String> runAlgorithm(@RequestParam String classSection, @RequestParam int newSize){

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

        try {
            ArrayList<Result> resultArr = Algorithm.doAlgorithm(result, newSize, schedule, classroomList);
            ArrayList<String> stringArr = new ArrayList<String>();
            System.out.println("Result at Controller: ");
            // Iterate through resultArr in reverse order and add each element to stringArr
            for (int i = resultArr.size() - 1; i >= 0; i--) {
                Result resultObj = resultArr.get(i);
                stringArr.add(resultObj.toString());
                System.out.println(resultObj.toString() + "/n");
            }
            return stringArr;
        } catch(Exception e) {
            //return "Class could not be rescheduled. There may be no classrooms that fit the new max size or no classrooms that can be easily rescheduled.";
            return null;
        }
    }
}
