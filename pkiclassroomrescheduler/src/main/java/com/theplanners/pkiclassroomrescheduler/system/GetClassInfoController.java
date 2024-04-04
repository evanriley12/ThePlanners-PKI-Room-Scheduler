package com.theplanners.pkiclassroomrescheduler.system;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@RestController
@RequestMapping("/api")
public class GetClassInfoController {

    private final Schedule schedule;
    private final ClassroomList classroomList;

    public GetClassInfoController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
        this.classroomList = classroomList;
    }

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

    @GetMapping("/all")
    @JsonManagedReference
    public ArrayList<Section> getMethodName() {
        return schedule.returnSchedule();
    }
    

}



