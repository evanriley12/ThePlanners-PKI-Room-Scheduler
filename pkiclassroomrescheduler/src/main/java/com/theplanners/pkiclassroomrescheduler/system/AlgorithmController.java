package com.theplanners.pkiclassroomrescheduler.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlgorithmController {

    private final Schedule schedule;
    private final Algorithm algorithm;
    private final ClassroomList classroomList;


    public AlgorithmController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
        this.algorithm = new Algorithm(schedule, classroomList);
    }

    @GetMapping("/algorithm")
    public String runAlgorithm(@RequestParam String classSection, @RequestParam int newSize){

        String[] courseSection = classSection.split("-");
        for(int i = 0 ; i < courseSection.length ; i++){
            courseSection[i] = courseSection[i].trim();
        }
        Section result = null;
        for(Course course : schedule.returnSchedule()){
           for(Section section : course.getSections()){
                if(section.getCourse().equals(courseSection[0]) && section.getSectionNumber() == Integer.parseInt(courseSection[1].replaceAll("[^\\d.]", ""))){
                    result = section;
                    break;
                }
           }
        }

        return algorithm.doAlgorithm(result, newSize);
    }
}
