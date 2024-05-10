package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;


@RestController
@RequestMapping("/api")
public class ClearResultsController {

   /**
    * The Autowired Schedule instance used for managing course assignments
    */
    private final Schedule schedule;


   public ClearResultsController(Schedule schedule, ClassroomList classroomList) {
        this.schedule = schedule;
    }

    @GetMapping("/reset")
    public ArrayList<Section> clearResults() {

      for(Section section : schedule.returnCSSchedule()){
         section.resetRoom();
      }
      return schedule.returnCSSchedule();
    }
}