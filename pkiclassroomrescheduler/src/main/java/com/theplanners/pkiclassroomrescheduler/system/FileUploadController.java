package com.theplanners.pkiclassroomrescheduler.system;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.theplanners.pkiclassroomrescheduler.system.MeetingTimeConverter.MeetingTime;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final Schedule schedule;

    public FileUploadController(Schedule schedule) {
        this.schedule = schedule;
    }

    @PostMapping("/upload")
    public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) {
        schedule.clearSchedule();
        List<String[]> parsedData; parsedData = new ArrayList<>();

        if (file.isEmpty()) {
            return parsedData;
        }

        try (InputStream inputStream = file.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRow;
            nextRow = csvReader.readNext();
            nextRow = csvReader.readNext();
            nextRow = csvReader.readNext();
            Course nextCourse = null;

            while ((nextRow = csvReader.readNext()) != null) { 
                if(nextRow[0] != ""){
                    if(nextCourse != null){
                        schedule.addCourse(nextCourse);
                    }
                    nextCourse = new Course(nextRow[0]);
                }
                else{
                    MeetingTime meetingTime = MeetingTimeConverter.parseMeetingTime(nextRow[11]);
                    Section section = new Section(nextRow[1], nextRow[3], nextRow[4], Integer.parseInt(nextRow[5].replaceAll("[^\\d.]", "")), 
                    nextRow[6], Integer.parseInt(nextRow[7].replaceAll("[^\\d.]", "")), nextRow[8], nextRow[9], meetingTime.days, 
                    meetingTime.startTime, meetingTime.endTime, nextRow[13], 
                    Integer.parseInt(nextRow[14].replaceAll("[^\\d.]", "")), nextRow[18], nextRow[34]);
                    if(nextCourse != null){
                        nextCourse.addSection(section);
                    }
                }
            } 

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        ArrayList<String> sections = new ArrayList<>();

        ArrayList<Course> completeSchedule = schedule.returnSchedule();

        for(Course course : completeSchedule){
            ArrayList<Section> courseSections = course.getSections();
            for(Section courseSection : courseSections){
                sections.add(courseSection.getCourse() + " - Section " + courseSection.getSectionNumber());
            }
        }

        return parsedData;
    }

}


